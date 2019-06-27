package edu.kit.informatik;

import java.util.LinkedList;

/**
 * This class contains methods to print Message in the terminal
 *
 * @author Armand
 * @version 1.0
 */

public class OutputUtilities {

    /**
     * This method prints the Message which shows the actual processed bucket
     *
     * @param i number of the bucket
     */
    public static void printBucket(int i) {
        Terminal.printLine("Processing Bucket " + i);
    }

    /**
     * This method prints the Message which shows which numbers are resoluted
     *
     * @param first  number which gets resoluted
     * @param second number which gets resoluted
     * @param result of the resolution
     */
    public static void printProcess(LinkedList<Integer> first, LinkedList<Integer> second, LinkedList<Integer> result) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("{");
        for (int i = 0; i < first.size(); i++) {
            outputBuilder.append(first.get(i));

            if (i < first.size() - 1)
                outputBuilder.append(",");
        }

        outputBuilder.append("} * {");

        for (int i = 0; i < second.size(); i++) {
            outputBuilder.append(second.get(i));

            if (i < second.size() - 1)
                outputBuilder.append(",");
        }

        outputBuilder.append("} = {");

        for (int i = 0; i < result.size(); i++) {
            outputBuilder.append(result.get(i));

            if (i < result.size() - 1)
                outputBuilder.append(",");
        }

        outputBuilder.append("}");

        Terminal.printLine(outputBuilder.toString());
    }

    /**
     * This method prints the message which shows if the result is satisfiable or not
     *
     * @param result when true sat is printed else UNSAT
     */
    public static void printResult(boolean result) {
        if (result)
            Terminal.printLine("SAT");
        else
            Terminal.printLine("UNSAT");
    }
}
