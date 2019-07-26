(jde-project-file-version "1.0")

(jde-set-variables
 '(jde-global-classpath (quote (
				"./src"
				"./classes"
				"./lib"
				"./images"

				"c:/app/jboss-3.2.3/server/default/lib/javax.servlet.jar"
				"c:/app/jboss-3.2.3/server/default/lib/jboss-j2ee.jar"
				)
			       )
			)
 '(jde-compile-option-directory "./classes")
 '(jde-run-working-directory ".")
 )
