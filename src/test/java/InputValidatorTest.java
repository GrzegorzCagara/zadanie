import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dashboard.nbp.service.InputValidator;
import pl.dashboard.nbp.service.InputValidatorImpl;

import java.time.DateTimeException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class InputValidatorTest {

    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        inputValidator = new InputValidatorImpl();
    }

    @Test
    public void shouldReturnTrueIfCorrectDataFormat() {
        String[] date = {"2016-11-05"};

        boolean result = inputValidator.validData(date);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseFalseIfWrongArg() {
        String[] date = {"2016-11-0"};

        boolean result = inputValidator.validData(date);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseFalseIfArgNull() {
        String[] date = null;

        boolean result = inputValidator.validData(date);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfDateParamInFuture() {
        String[] date = {"2222-12-12"};

        boolean result = inputValidator.validData(date);

        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfDateParamNotFuture() {
        String[] date = {"2016-12-12"};

        boolean result = inputValidator.validData(date);

        assertTrue(result);
    }

    @Test(expected = DateTimeException.class)
    public void shouldThrowDateTimeExceptionIfWrongFormat() {
        String[] date = {"2016-33-12"};

        inputValidator.validData(date);
    }
}
