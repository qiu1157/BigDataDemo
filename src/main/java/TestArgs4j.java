import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qiuxiangu on 2016/8/29.
 */
public class TestArgs4j {
    public Logger logger = LoggerFactory.getLogger(TestArgs4j.class);
    @Option(name = "-spoutNum", usage = "spout task num")
    private static int spoutNum = 40;
    @Option(name = "-boltNum", usage = "boltNum task num")
    private static int boltNum = 40;
    @Option(name = "-workerNum", usage = "workerNum num")
    private static int workerNum = 40;
    @Option(name = "-ackerNum", usage = "ackerNum num")
    private static int ackerNum = 40;
    @Option(name = "-timeout", usage = "timeout num")
    private static int timeout = 30;
    @Option(name = "-maxPending", usage = "maxPending num")
    private static int maxPending = 1000;
    @Option(name = "-freqSecs", usage = "freqSecs num")
    private static int freqSecs = 10;

    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
//            logger.error("Usage: {}", parser.printExample(ALL));
            e.printStackTrace();
        }
        System.out.println(parser.printExample(OptionHandlerFilter.ALL));
//        System.out.println("spoutTaskNum=="+ spoutTaskNum);
    }

    public static void main(String[] args) {
//        System.out.println(args[0]);
//        System.out.println(args[1]);
        new TestArgs4j().doMain(args);

    }
}
