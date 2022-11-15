
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.util.List;

public class main {
    
    private static ArrayList<String> Column1 = new ArrayList<String>();
    static String input = "";
    static String input2 = "";
    static ArrayList<String> country_Name = new ArrayList<String>();
    static ArrayList<Integer> total_Cases = new ArrayList<Integer>();
    static ArrayList<Integer> total_Deaths = new ArrayList<Integer>();
    static ArrayList<Double> total_Tests = new ArrayList<Double>();
    static ArrayList<Integer> population = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        // Column1 = loadStringList("Covid Live - Covid Live (1).csv");
        double AvgTests = 0.0;
        country_Name = loadStringList("country.txt");
        total_Cases = loadIntegerList("cases.txt");
        total_Deaths = loadIntegerList("deaths.txt");
        total_Tests = loadDoubleList("tests.txt");
        population = loadIntegerList("pop.txt");
        
        // System.out.println(Column1);
        

        Scanner scanner = new Scanner(System.in);

        while (true) { // menu for the user
            System.out.println("Welcome to the Covid Manipulation database using the COVID LIVE dataset provided by Kaggles \n To begin:");
            System.out.println(
                    "Please enter your selection:\n exit - ext program \n SearchP - search results from database by country \n SearchD - Prints amount of deaths by country \n AvgT - Averages the amount of tests taken in the world based of the time this data was taken \n LeastS - Prints the population counts form least to greatest \n MaxP - Prints the Countries in Alphabetical order, the most amount of deaths, tets, cases and the Biggest Population");

            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.err.println("Now Exiting program...");
                break;
            } else if (input.equals("DeathP")) {
                filterPrint();
            } else if (input.equals("SearchP")) {
                System.out.println("Please enter the country you would like information on:\t");
                input2 = scanner.nextLine();
                Submenu();
            } else if (input.equals("AvgT")) {
                AvgTests = sumT() / total_Tests.size();
                System.out.println("The average amount of tests in the world: "+ AvgTests);
            } else if(input.equals("LeastS")) {
                SortLeastGreat();
            } else if(input.equals("MaxP")) {
                MaxP();
            } else if (input.equalsIgnoreCase("man")) {
                AlgMan();
            }
        }

        scanner.close();

        
        
    }

    private static void AlgMan() {
        System.out.println("DeathP - This algorithim prints off the country followed by the amount deaths of each country. \n I was originally interested in this result for 2 main reasons, First it shows the tole that Covid-19 has taken on each Country and Second, shows the amount of people that this pandemic has affected. ");
        System.out.println("SearchP - The searchP algorithim allows the user to search through the data base to find and print the statistics of a certain country. \n This could be used to make important marketing and sometimes political descisions that could affect policies in the bussiness and the country.");
        System.out.println("AvgT - This algorithim pulls the average amount of tests conducted y all of the 230 countries in the data set. \n  I can safely infere that each country on average does conduct a rather large amount of tests in relevant to the population of the USA however worldwide it is not that many");
        System.out.println("LeastS - This algorithim prints out the population amounts from least to greatest. \n while not exactly usefull by itself this and the MAXP algorithim can be used to input data from this system in to other data processing software");
        System.out.println("MaxP - This Algorythim orders the countries by alphabetical order and outputs the highest amount of Cases, Tests, Deaths and the biggest population. \n This is usefull in the event you would like a straight highest value instead of doing the math youself and trying to figure it out");
    }


    private static void MaxP() {
        Collections.sort(country_Name);
        for (int i = 0; i < country_Name.size(); i++) {
            System.out.println(country_Name.get(i));
        }
            System.out.println(Collections.max(total_Cases) + "\t" + Collections.max(total_Deaths) + "\t" + Collections.max(total_Tests) + "\t" + Collections.max(population));

        return;
    }




    private static void SortLeastGreat() {
            Collections.sort(population);
            Collections.reverse(country_Name);
            for (int i = 0; i < country_Name.size(); i++) {
                System.out.println(country_Name.get(i) + "\t" + population.get(i));
            }
            return;
    }


    private static double sumT() {
        double sum = 0.0;
        for (int i = 0; i < country_Name.size(); i++) {
            sum += total_Tests.get(i);
        }
        return sum;
    }

    private static void Submenu() {

        for (int i = 0; i < country_Name.size(); i++) {
            if (input2.equals(country_Name.get(i))) {
                System.out.println(country_Name.get(i) + "\t" + total_Cases.get(i) + "\t" + total_Deaths.get(i) + "\t"
                        + total_Tests.get(i) + "\t" + population.get(i));
                return;
            }
        }//search print method
    }//the sub menu for the search print function

    private static void filterPrint() {

        for (int i = 0; i < country_Name.size(); i++) {
            System.out.println(country_Name.get(i) + "\t" + total_Deaths.get(i));
        }
    }//prints out the amount of deaths by country

    // load string list method
    private static ArrayList<String> loadStringList(String filename) {
        ArrayList<String> temp = new ArrayList<String>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            while (file.ready()) {
                temp.add(file.readLine());
            } // end while
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return temp;
    }

    // load integer list method
    private static ArrayList<Integer> loadIntegerList(String filename) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            while (file.ready()) {
                String nextLineS = file.readLine();
                try {
                    int nextLineI = Integer.parseInt(nextLineS);
                    temp.add(nextLineI);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            } // end while
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return temp;

    }

    // load double list method
    private static ArrayList<Double> loadDoubleList(String filename) {
        ArrayList<Double> temp = new ArrayList<Double>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            while (file.ready()) {
                String nextLineS = file.readLine();
                try {
                    double nextLineD = Double.parseDouble(nextLineS);
                    temp.add(nextLineD);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            } // end while
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return temp;
    }

}

// algorythim requirements

/*
 * Once your data is ready, you are to write 5 algorithms that process and
 * analyze your data in a way that is interesting to you. To count as an
 * Algorithm, it must use a FOR loop to filter and/or generate a statistic. For
 * each of the 5 algorithms:
 * In println statements, explain or clearly title what your algorithm is about
 * to do. (1 or 2 sentences is fine.)
 * Run the algorithm, printing the results
 * In println statements answer ONE of the following questions. (1 or 2
 * sentences is fine.)
 * Why were you interested in this result?
 * OR What conclusion can you infer from this result?
 * OR How could someone (perhaps in the business world) use this result to help
 * make a decision?
 */
