package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;
import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;
import java.util.Scanner;



public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		s.runCycle();
		print(s);
		s.raiseStart();
		s.runCycle();
		Scanner scanner = new Scanner(System.in);
        String input;
		while (true) {
            System.out.print("adjon meg egy eseményt vagy írja be az 'exit' parancsot: ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Az alkalmazás leáll.");
                break;
            } else if (input.equalsIgnoreCase("start")) {
                s.raiseStart();
            } else if (input.equalsIgnoreCase("white")) {
                s.raiseWhite();
            } else if (input.equalsIgnoreCase("black")) {
                s.raiseBlack();
            } else {
                System.out.println("Érvénytelen esemény.");
                continue;
            }
            s.runCycle();
            print(s);
        }
		s.runCycle();
		print(s);
		System.exit(0);
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
}
