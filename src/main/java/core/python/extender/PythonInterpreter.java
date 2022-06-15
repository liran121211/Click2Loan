package core.python.extender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonInterpreter {

    public PythonInterpreter(){}

    public static void exec(String filename) {
        String s = null;

        try {

            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(String.format("python src\\main\\java\\core\\bin\\python\\%s", filename));

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //Here is the standard output of the command
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            //Here is the standard error of the command (if any)
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            //exception happened - here's what I know:
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
