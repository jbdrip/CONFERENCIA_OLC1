SET JFLEX_HOME= C:\libs\JFLEX\jflex-1.7.0
cd C:\Users\javie\Documents\NetBeansProjects\Recursividad_Conferencia_OLC1\src\Analyzers
java -jar %JFLEX_HOME%\lib\jflex-full-1.7.0.jar scanner.l
pause

SET JFLEX_HOME= C:\libs\JFLEX
cd C:\Users\javie\Documents\NetBeansProjects\Recursividad_Conferencia_OLC1\src\Analyzers
java -jar %JFLEX_HOME%\java-cup-11b.jar -parser parser parser.cup
pause