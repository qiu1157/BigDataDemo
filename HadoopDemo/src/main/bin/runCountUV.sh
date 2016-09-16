#!/bin/sh
start_dt='2016-01-01'
end_dt='2016-01-01'
path="/user/recsys/rec.db/rec_3/"
#uv字段在第几列, 从0开始数
col=3
#redis存放key
redisKey="req_uv_q1"
while [ ${start_dt} != ${end_dt} ]
do
    hadoop jar ./CounUV.jar \
    -D input=${path}${start_dt} \
    -D col=${col} \
    -D redisKey=${redisKey}

done