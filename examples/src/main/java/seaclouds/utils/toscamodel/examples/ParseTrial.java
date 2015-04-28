/*
 * Copyright 2015 Universita' di Pisa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package seaclouds.utils.toscamodel.examples;
import seaclouds.utils.toscamodel.*;

/* FileReader */
import java.io.*;

/**
 * Created by Mattia Buccarella
 *
 **/
public class ParseTrial {
	
	static void FatalError(String errorMessage) {
		System.out.println("FATAL ERROR: " + errorMessage);
		System.out.println("The application will now terminate.");
		System.out.println();
		System.exit(2);
	}
	
	static void printUsage() {
		System.out.println("Usage java ParseTrial <file name>");
		System.out.println();
		System.exit(1);
	}
	
	public static void main(String[] args) {
		if(args.length < 1)
			printUsage();
			
		/* opening file */
		String yamlFileName = args[0];
		FileReader fr = null;
		try { fr = new FileReader(yamlFileName); }
		catch(FileNotFoundException fnfex) {
			System.out.println("Cannot find specified file: " + yamlFileName);
			printUsage();
			return;
		}
		
		/* parsing */
		IToscaEnvironment tyaml = Tosca.newEnvironment();
		tyaml.readFile(fr, false);
		
		/* closing */
		try { fr.close(); }
		catch(IOException ioex) {
			FatalError(ioex.getMessage());
		}
		
		/* printing stuff */
		for (INodeTemplate nodeType : tyaml.getNodeTemplatesOfType((INodeType) tyaml.getNamedEntity("tosca.nodes.Root")))
		    System.out.println(nodeType.allAttributes().toString());
		

		return;
	}
	
}