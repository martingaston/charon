import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("The App class")
class AppTest {
  @DisplayName("Has a functioning test runner")
  @Test
  void testSimpleAddition() {
    assertThat(1+1).isEqualTo(2);
  }
}
