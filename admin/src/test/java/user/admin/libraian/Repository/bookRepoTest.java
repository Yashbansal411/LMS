package user.admin.libraian.Repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class bookRepoTest {
    /**
     * Method under test: {@link bookRepo#findAllCustom(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAllCustom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   In order to prevent findAllCustom(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findAllCustom(String).
        //   See https://diff.blue/R013 to resolve this issue.

        bookRepo bookRepo = null;
        bookRepo.findAllCustom("foo");
    }
}

