package lotto.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.constants.ErrorMessage.PURCHASE_MONEY_ONLY_CAN_NUMBER;
import static lotto.constants.ErrorMessage.PURCHASE_MONEY_ONLY_CAN_THOUSAND_UNIT;
import static lotto.util.PurchaseUtils.getThousandUnitCount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseValidationTest {
    @Test
    @DisplayName("1,000원 단위로 로또 구매한 수")
    void 천원_단위로_로또_구매한_수() {
        int thousandUnitCount = getThousandUnitCount("10000");
        assertThat(thousandUnitCount).isEqualTo(10);
    }

    @Test
    @DisplayName("숫자로 로또 구매하지 않으면 예외가 발생한다.")
    void 숫자로_로또_구매하지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> PurchaseValidation.validateNumericAmount("천원"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_MONEY_ONLY_CAN_NUMBER.getErrorMessage());
    }

    @Test
    @DisplayName("1,000원 단위로 로또 구매하지 않으면 예외가 발생한다.")
    void 천원_단위로_로또_구매하지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> PurchaseValidation.validateDivisibleThousand(1001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_MONEY_ONLY_CAN_THOUSAND_UNIT.getErrorMessage());
    }
}