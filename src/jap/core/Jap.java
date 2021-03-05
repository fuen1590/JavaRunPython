package jap.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jap.exception.JapException;

public class Jap {
    private String env_url, py_url;
    private String[] args;
    private String[] arguments;
    private final String version = "1.0";

    private final void print_logo(){
        /**
         *
         Version: 1.0
         ========================================
         |            /         \ |              |
         ====|     |=/     =     \|     |===|    |
             |     |/     / \     |     |===|    |
             |     /     /===\    |     ________/
             |    /     /     \   |     |
            /    /     /       \  |     |
          /_____/_____/         \_|_____|
         ========================================
         Broking the wall of language.
         ========================================
         */
        System.out.println("  " + "\033[44m" + "\033[30m" + " Jap Version: " + version + " \033[m");
        System.out.println("\033[32m" + "  ========================================");
        System.out.println("\033[32m" + "  |            /         \\ |              |");
        System.out.println("\033[32m" + "  ====|     |=/     =     \\|     |===|    |");
        System.out.println("\033[32m" + "      |     |/     / \\     |     |===|    |");
        System.out.println("\033[32m" + "      |     /     /===\\    |     ________/ ");
        System.out.println("\033[32m" + "      |    /     /     \\   |     |         ");
        System.out.println("\033[32m" + "     /    /     /       \\  |     |         ");
        System.out.println("\033[32m" + "   /_____/_____/         \\_|_____|");
        System.out.println("\033[32m" + "  ========================================");
        System.out.println("\033[32m" + "   Broking the wall of language.");
        System.out.println("\033[32m" + "  ========================================");
    }

    private void print_log(String msg, int type){
        String msg_type = type ==0?"\033[32m":"\033[31m";
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String pre_s = msg_type + "[Jap log | " + formatter.format(date) + "]:" + " \033[m";
        System.out.println(pre_s + msg);
    }

    private void log_err(String msg){
        print_log(msg, 1);
    }

    private void log_nor(String msg){
        print_log(msg, 0);
    }

    private void print_msg(String msg){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String print = "\033[34m" + "[Python Print | " + formatter.format(date) + "]:" + " \033[m";
        System.out.println(print + msg);
    }

    public Jap(String env_url, String py_url) {
        this.env_url = env_url;
        this.py_url = py_url;
        this.arguments = new String[2];
        this.arguments[0] = this.env_url;
        this.arguments[1] = this.py_url;
        print_logo();
        log_nor("Jap has created successfully.");
    }

    public Jap(String env_url, String py_url, String[] args) {
        this.env_url = env_url;
        this.py_url = py_url;
        this.args = args;
        this.arguments = new String[this.args.length + 2];
        this.arguments[0] = this.env_url;
        this.arguments[1] = this.py_url;
        int i = 2;
        for (String arg : args) {
            this.arguments[i++] = arg;
        }
        print_logo();
        log_nor("Jap has created successfully.");
    }

    public boolean run() {
        log_nor("Creating runtime successfully.Running the target python file...");
        try {
            Process process = Runtime.getRuntime().exec(this.arguments);
            int re = process.waitFor();
            if (re == 0){
                log_nor("Running successful.");
                return true;
            }
            else {
                log_err("Running Failed.");
                throw new JapException(JapException.ERROR_CODE_0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Double> run_return_list_1D() {
        log_nor("Creating runtime successfully.Running the target python file...");
        ArrayList<Double> result_list = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(this.arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tem = line.split(", ");
                try {
                    for (String s : tem) {
                        result_list.add(Double.valueOf(s));
                    }
                } catch (Exception e) {
                    log_err("Getting the result Failed.");
                    throw new JapException(JapException.ERROR_CODE_1);
                }
            }
            log_nor("Getting the result(1D matrix) successful");
            in.close();
            int re = process.waitFor();
            if(re == 0) log_nor("Running successful.");
            else throw new JapException(JapException.ERROR_CODE_0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_list;
    }

    public ArrayList<ArrayList<Double>> run_return_list_2D() {
        log_nor("Creating runtime successfully.Running the target python file...");
        ArrayList<ArrayList<Double>> result_list = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(this.arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                ArrayList<Double> tem_list = new ArrayList<>();
                String[] tem = line.split(", ");
                try {
                    for (String s : tem) {
                        tem_list.add(Double.valueOf(s));
                    }
                    result_list.add(tem_list);
                } catch (Exception e) {
                    log_err("Getting the result failed.");
                    throw new JapException(JapException.ERROR_CODE_1);
                }
            }
            log_nor("Getting the result(2D matrix) successful");
            in.close();
            int re = process.waitFor();
            if(re == 0) log_nor("Running successful.");
            else throw new JapException(JapException.ERROR_CODE_0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_list;
    }

    public String run_return_string() {
        log_nor("Creating runtime successfully.Running the target python file...");
        try {
            Process process = Runtime.getRuntime().exec(this.arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = in.readLine()) != null) {
                print_msg(line);
                result.append(line + "\n");
            }
            log_nor("Getting the result(String) successful");
            in.close();
            int re = process.waitFor();
            if(re == 0) log_nor("Running successful.");
            else throw new JapException(JapException.ERROR_CODE_0);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
