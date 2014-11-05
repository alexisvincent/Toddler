package toddler;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 *
 * @author alexisvincent
 */
public class Toddler {

    private final DefaultExecutor executor;
    private final ExecuteResultHandler resultHandler;

    private DataInputStream inputStream;

    private File ffmpeg;
    private File inputFile, outputFile;

    private Target target;

    public Toddler() {
        executor = new DefaultExecutor();

        if (System.getProperty("os.name").startsWith("Windows")) {
            ffmpeg = new File("./exe/ffmpeg.exe");
        } else {
            ffmpeg = new File("./exe/ffmpeg");
        }

        PipedOutputStream output = new PipedOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(output);
        this.executor.setStreamHandler(pumpStreamHandler);

        try {
            inputStream = new DataInputStream(new PipedInputStream(output));
        } catch (IOException ex) {
            Logger.getLogger(Toddler.class.getName()).log(Level.SEVERE, null, ex);
        }

        resultHandler = new ExecuteResultHandler() {

            @Override
            public void onProcessComplete(int i) {
                
            }

            @Override
            public void onProcessFailed(ExecuteException ee) {

            }
        };
    }

    private CommandLine buildCommand() {
        HashMap map = new HashMap();
        CommandLine cmdLine = new CommandLine(getBinary().getAbsolutePath());
        cmdLine.setSubstitutionMap(map);

        map.put("inputFile", getInputFile());
        map.put("outputFile", getOutputFile());

        if (getInputFile() != null) {
            cmdLine.addArgument("-i");
            cmdLine.addArgument("${inputFile}", false);
        }

        if (target != null) {
            cmdLine.addArgument("-target");
            cmdLine.addArgument(getTarget().toString());
        }

        if (getOutputFile() != null) {
            cmdLine.addArgument("${outputFile}", false);
        }

        return cmdLine;
    }

    public void execute() {
        try {
            executor.execute(buildCommand(), this.resultHandler);
        } catch (IOException ex) {
        }
    }

    public InputStream getProcessOutputStream() {
        return this.inputStream;
    }

    public void setTimeout(int timeout) {
        executor.setWatchdog(new ExecuteWatchdog(timeout));
    }

    public File getBinary() {
        return ffmpeg;
    }

    public void setBinary(File ffmpeg) {
        this.ffmpeg = ffmpeg;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public static class Target {

        public Type type;
        public Standard standard;

        public enum Type {

            vcd, svcd, dvd, dv, dv50
        }

        public enum Standard {

            pal, ntsc, film
        }

        public Target(Type type, Standard standard) {
            this.type = type;
            this.standard = standard;
        }

        @Override
        public String toString() {
            return standard + "-" + type;
        }
    }
}
