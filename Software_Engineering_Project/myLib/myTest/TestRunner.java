package myTest;
import myAdapter.*;


import org.junit.runner.JUnitCore;

import com.sun.net.httpserver.Authenticator.Failure;
import junit.runner.Version;
public class TestRunner {

	public static void main(String[] args) {
		org.junit.runner.Result result = JUnitCore.runClasses(AllTests.class);
		for(org.junit.runner.notification.Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println();
		System.out.print("Versione usata di JUnit: ");
		System.out.println(Version.id());
		System.out.print("Numero test eseguiti: ");
		System.out.println(result.getRunCount());
		System.out.print("Numero test falliti: ");
		System.out.println(result.getFailureCount());
		System.out.println("A volte, nei vari metodi, ho eseguito anche delle stampe, visibili sopra.\nTali stampe possono essere ignorate");		
	}
}
