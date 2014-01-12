package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DateQuizTest.class, EnumerationExerciseTest.class,
		ExerciseCatalogTest.class, MultipleChoiceExerciseTest.class,
		QuizExerciseTest.class, QuizTest.class,
		SimpleExerciseTest.class, StudentTest.class })
public class SuiteForAllTests {

}
