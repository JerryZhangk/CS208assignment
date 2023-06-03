//package test;
//import java.io.*;
//import java.util.Random;
//
///**
// * Auto Tester Machine
// * Usage:
// * 1. Change the 'seed' and 'total' to the same as your partner
// * 2. Run main()
// * 3. Check whether your outputs are same
// */
//public class Tester {
//    /**
//     * Settings
//     */
//    final static int seed = 10088;
//    final static int total = 100;
//
//
//    private static final InputStream systemIn = System.in;
//    private static final PrintStream systemOut = System.out;
//
//    private static ByteArrayInputStream testIn;
//    private static ByteArrayOutputStream testOut;
//
//    public static void setUpOutput() {
//        testOut = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(testOut));
//    }
//
//    private static void provideInput(String data) {
//        testIn = new ByteArrayInputStream(data.getBytes());
//        System.setIn(testIn);
//    }
//
//    private static String getOutput() {
//        return testOut.toString();
//    }
//
//    public static void main(String[] args) {
//
//
//        Random random = new Random(seed);
//
//        for (int i = 0; i < total; i++) {
//            setUpOutput();
//
//
//            StringBuilder input = new StringBuilder();
//            int n = random.nextInt(1, 10000);
//            input.append(n).append(" ");
//            int m = random.nextInt(1, 10000);
//            input.append(m).append("\n");
//            for (int j = 0; j < n; j++) {
//                input.append(random.nextInt(1, 10000)).append(" ");
//            }
//            for (int j = 0; j < m; j++) {
//                int a = random.nextInt(0, 2);
//                int b = random.nextInt(1, n + 1);
//                int c;
//                if (a == 0) {
//                    c = random.nextInt(1, 10000);
//                } else {
//                    c = random.nextInt(b, n + 1);
//                }
//
//                input.append(a).append(" ");
//                input.append(b).append(" ");
//                input.append(c).append("\n");
//            }
//
//
////            systemOut.println(input);
//
//            provideInput(input.toString());
//
//
////            assignment9.assignment9_Q1.main(new String[0]);
//
//            String output = getOutput().trim();
//
//            setUpOutput();
//            provideInput(input.toString());
//            test.main(new String[0]);
//            String standardOutput = getOutput().trim();
//
//            if (!output.equals(standardOutput)){
//                systemOut.println("======================================");
//                systemOut.println("Your Answer");
//                systemOut.println(output);
//                systemOut.println("Standard Answer");
//                systemOut.println(standardOutput);
//                systemOut.println("Input");
//                systemOut.println(input);
//                systemOut.println("======================================");
//
//
//            }
//        }
//
//    }
//}
