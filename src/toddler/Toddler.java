package toddler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFileChooser;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;

/**
 *
 * @author alexisvincent
 */
public class Toddler {

    private final DefaultExecutor executor;

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

    public int execute() {
        int exit = -1;
        try {
            exit = executor.execute(buildCommand());
        } catch (IOException ex) {

        }
        return exit;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Toddler toddler = new Toddler();
        
        JFileChooser chooser = new JFileChooser(new File("./exe"));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    toddler.setInputFile(chooser.getSelectedFile());
        }
        
        toddler.setOutputFile(new File("exe/output.vob"));
        toddler.setTarget(new Target(Target.Type.dvd, Target.Standard.pal));
        toddler.execute();
    }
}
