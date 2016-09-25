package com.jd.sz;

import com.hadoop.mapreduce.LzoTextInputFormat;
import com.jd.www.redis.work.RedisUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by qiuxiangu on 2016/9/16.
 */
public class CountUV {
    private class MyMapper extends Mapper<Object, Text, Text, Text> {
        private Configuration conf;
        private RedisUtil redis;
        private int col ;
        private HashSet<String> set ;
        private String redisKey = "count_uv";
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            conf = context.getConfiguration();
            redis = new RedisUtil();
            col = conf.getInt("col", 0);
            redisKey = conf.get("redisKey");
            set = new HashSet<String>();
        }

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] columns = value.toString().split("\t");
            String uuid = columns[col];
            set.add(uuid);
            if (set.size() > 10000) {
                redis.pfadd(redisKey, set);
                set.clear();
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            redis.pfadd(redisKey, set);
            set.clear();
        }
    }

    private class MyReducer extends Reducer<Text, Text, Text, Text> {}

    public void run(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        Job job = Job.getInstance(conf, "Count_UV");
        String path = conf.get("input");
        job.setJarByClass(CountUV.class);
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(LzoTextInputFormat.class);
        job.setNumReduceTasks(0);
        job.setOutputFormatClass(MyTextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(path));
        FileOutputFormat.setOutputPath(job, new Path("/tmp/tmp"));
        if(!job.waitForCompletion(true)) {
            return;
        }

    }
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        CountUV c = new CountUV();
        c.run(args);
    }

}
