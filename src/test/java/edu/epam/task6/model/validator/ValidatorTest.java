package edu.epam.task6.model.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ValidatorTest {

    Validator validator = new Validator();

    @Test
    public void validateIdTestTrue() {
        String id = "123";
        boolean actual = validator.validateId(id);
        assertTrue(actual);
    }

    @Test
    public void validateIdTestFalse1() {
        String id = "-1";
        boolean actual = validator.validateId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse2() {
        String id = "9323372036854775807";
        boolean actual = validator.validateId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse3() {
        String id = "";
        boolean actual = validator.validateId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse4() {
        String id = "   ";
        boolean actual = validator.validateId(id);
        assertFalse(actual);
    }

    @Test
    public void validateIdTestFalse5() {
        String id = "<script>";
        boolean actual = validator.validateId(id);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestTrue1() {
        String email = "vasia.pupkin@gmail.com";
        boolean actual = validator.validateEmail(email);
        assertTrue(actual);
    }

    @Test
    public void validateEmailTestTrue2() {
        String email = "vasia.pupkin@mail.ru";
        boolean actual = validator.validateEmail(email);
        assertTrue(actual);
    }

    @Test
    public void validateEmailTestTrue3() {
        String email = "____vasia.pupkin@bsuir.by";
        boolean actual = validator.validateEmail(email);
        assertTrue(actual);
    }

    @Test
    public void validateEmailTestFalse1() {
        String email = "vasia.pupkin@mail";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestFalse2() {
        String email = "vasia.pupkin@mail.r";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestFalse3() {
        String email = "vasia.pupkinmail.r";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestFalse4() {
        String email = "vasia.pupkin@mail.r@mail.ru";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestFalse5() {
        String email = "тест@mail.ru";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }
    @Test
    public void validateEmailTestFalse6() {
        String email = "";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestFalse7() {
        String email = "  ";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateEmailTestFalse8() {
        String email = "<script>";
        boolean actual = validator.validateEmail(email);
        assertFalse(actual);
    }

    @Test
    public void validateLoginTestTrue1() {
        String login = "Daetwen";
        boolean actual = validator.validateLogin(login);
        assertTrue(actual);
    }

    @Test
    public void validateLoginTestTrue2() {
        String login = "Daetwen123.some-thing_Daetwen123.some-th";
        boolean actual = validator.validateLogin(login);
        assertTrue(actual);
    }

    @Test
    public void validateLoginTestFalse1() {
        String login = "Daetwen123.some-thing_Daetwen123.some-thh";
        boolean actual = validator.validateLogin(login);
        assertFalse(actual);
    }

    @Test
    public void validateLoginTestFalse2() {
        String login = "";
        boolean actual = validator.validateLogin(login);
        assertFalse(actual);
    }

    @Test
    public void validateLoginTestFalse3() {
        String login = "   ";
        boolean actual = validator.validateLogin(login);
        assertFalse(actual);
    }

    @Test
    public void validateLoginTestFalse4() {
        String login = "Daet<script>dssd";
        boolean actual = validator.validateLogin(login);
        assertFalse(actual);
    }

    @Test
    public void validateMinMaxRangeTestTrue1() {
        String min = "1";
        String max = "9223372036854775807";
        boolean actual = validator.validateMinMaxRange(min, max);
        assertTrue(actual);
    }

    @Test
    public void validateMinMaxRangeTestFalse1() {
        String min = "";
        String max = "9223372036854775807";
        boolean actual = validator.validateMinMaxRange(min, max);
        assertFalse(actual);
    }

    @Test
    public void validateMinMaxRangeTestFalse2() {
        String min = "  ";
        String max = "9223372036854775807";
        boolean actual = validator.validateMinMaxRange(min, max);
        assertFalse(actual);
    }

    @Test
    public void validateMinMaxRangeTestFalse3() {
        String min = "9223372036854775807";
        String max = "1";
        boolean actual = validator.validateMinMaxRange(min, max);
        assertFalse(actual);
    }

    @Test
    public void validateMinMaxRangeTestFalse4() {
        String min = "-1";
        String max = "9223372036854775807";
        boolean actual = validator.validateMinMaxRange(min, max);
        assertFalse(actual);
    }

    @Test
    public void validateMinMaxRangeTestFalse5() {
        String min = "1";
        String max = "<script>";
        boolean actual = validator.validateMinMaxRange(min, max);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestTrue1() {
        String name = "Vlad";
        boolean actual = validator.validateName(name);
        assertTrue(actual);
    }

    @Test
    public void validateNameTestTrue2() {
        String name = "Владислав";
        boolean actual = validator.validateName(name);
        assertTrue(actual);
    }

    @Test
    public void validateNameTestFalse1() {
        String name = "vlad";
        boolean actual = validator.validateName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse2() {
        String name = "владислав";
        boolean actual = validator.validateName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse3() {
        String name = "";
        boolean actual = validator.validateName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse4() {
        String name = "  ";
        boolean actual = validator.validateName(name);
        assertFalse(actual);
    }

    @Test
    public void validateNameTestFalse5() {
        String name = "<script>";
        boolean actual = validator.validateName(name);
        assertFalse(actual);
    }
    @Test
    public void validateNameTestFalse6() {
        String name = "Владиславладиславладиславладиславладислав";
        boolean actual = validator.validateName(name);
        assertFalse(actual);
    }

    @Test
    public void validatePasswordTestTrue1() {
        String password = "-_12345Vlad!@#$%^&*(fgh)_12345Vlad!@_12345Vla";
        boolean actual = validator.validatePassword(password);
        assertTrue(actual);
    }
    @Test
    public void validatePasswordTestTrue2() {
        String password = "12345Vlad";
        boolean actual = validator.validatePassword(password);
        assertTrue(actual);
    }

    @Test
    public void validatePasswordTestFalse1() {
        String password = "-_12345Vlad!@#$%^&*(fgh)_12345Vlad!@_12345Vlad";
        boolean actual = validator.validatePassword(password);
        assertFalse(actual);
    }

    @Test
    public void validatePasswordTestFalse2() {
        String password = "12345Vl";
        boolean actual = validator.validatePassword(password);
        assertFalse(actual);
    }

    @Test
    public void validatePasswordTestFalse3() {
        String password = "";
        boolean actual = validator.validatePassword(password);
        assertFalse(actual);
    }

    @Test
    public void validatePasswordTestFalse4() {
        String password = "  ";
        boolean actual = validator.validatePassword(password);
        assertFalse(actual);
    }

    @Test
    public void validatePasswordTestFalse6() {
        String password = "1234dsdsG<script>";
        boolean actual = validator.validatePassword(password);
        assertFalse(actual);
    }

    @Test
    public void validatePriceTestTrue1() {
        String price = "9223372036854775807";
        boolean actual = validator.validatePrice(price);
        assertTrue(actual);
    }

    @Test
    public void validatePriceTestFalse1() {
        String price = "";
        boolean actual = validator.validatePrice(price);
        assertFalse(actual);
    }

    @Test
    public void validatePriceTestFalse2() {
        String price = "  ";
        boolean actual = validator.validatePrice(price);
        assertFalse(actual);
    }

    @Test
    public void validatePriceTestFalse3() {
        String price = "<script>";
        boolean actual = validator.validatePrice(price);
        assertFalse(actual);
    }

    @Test
    public void validatePriceTestFalse4() {
        String price = "12345fgr";
        boolean actual = validator.validatePrice(price);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestTrue1() {
        String description= "Hello";
        boolean actual = validator.validateDescription(description);
        assertTrue(actual);
    }

    @Test
    public void validateDescriptionTestFalse1() {
        String description= "something";
        boolean actual = validator.validateDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestFalse2() {
        String description= "";
        boolean actual = validator.validateDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestFalse3() {
        String description= "  ";
        boolean actual = validator.validateDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validateDescriptionTestFalse4() {
        String description= "D  <script>";
        boolean actual = validator.validateDescription(description);
        assertFalse(actual);
    }

    @Test
    public void validateCommentTestTrue1() {
        String comment = "Hello";
        boolean actual = validator.validateComment(comment);
        assertTrue(actual);
    }

    @Test
    public void validateCommentTestTrue2() {
        String comment = "something";
        boolean actual = validator.validateComment(comment);
        assertTrue(actual);
    }

    @Test
    public void validateCommentTestFalse1() {
        String comment = "";
        boolean actual = validator.validateComment(comment);
        assertFalse(actual);
    }

    @Test
    public void validateCommentTestFalse2() {
        String comment = "  ";
        boolean actual = validator.validateComment(comment);
        assertFalse(actual);
    }

    @Test
    public void validateCommentTestFalse3() {
        String comment = "D  <script>";
        boolean actual = validator.validateComment(comment);
        assertFalse(actual);
    }

    @Test
    public void validateSizeTestTrue1() {
        String size = "50";
        boolean actual = validator.validateSize(size);
        assertTrue(actual);
    }

    @Test
    public void validateSizeTestTrue2() {
        String size = "2147483647";
        boolean actual = validator.validateSize(size);
        assertTrue(actual);
    }

    @Test
    public void validateSizeTestFalse1() {
        String size = "-1";
        boolean actual = validator.validateSize(size);
        assertFalse(actual);
    }

    @Test
    public void validateSizeTestFalse2() {
        String size = "2147483648";
        boolean actual = validator.validateSize(size);
        assertFalse(actual);
    }

    @Test
    public void validateSizeTestFalse3() {
        String size = "";
        boolean actual = validator.validateSize(size);
        assertFalse(actual);
    }

    @Test
    public void validateSizeTestFalse4() {
        String size = "   ";
        boolean actual = validator.validateSize(size);
        assertFalse(actual);
    }

    @Test
    public void validateSizeTestFalse5() {
        String size = "<script>";
        boolean actual = validator.validateSize(size);
        assertFalse(actual);
    }

    @Test
    public void validateDiscountTestTrue1() {
        String discount = "5";
        boolean actual = validator.validateDiscount(discount);
        assertTrue(actual);
    }

    @Test
    public void validateDiscountTestTrue2() {
        String discount = "100";
        boolean actual = validator.validateDiscount(discount);
        assertTrue(actual);
    }

    @Test
    public void validateDiscountTestFalse1() {
        String discount = "-1";
        boolean actual = validator.validateDiscount(discount);
        assertFalse(actual);
    }

    @Test
    public void validateDiscountTestFalse2() {
        String discount = "102";
        boolean actual = validator.validateDiscount(discount);
        assertFalse(actual);
    }

    @Test
    public void validateDiscountTestFalse3() {
        String discount = "";
        boolean actual = validator.validateDiscount(discount);
        assertFalse(actual);
    }

    @Test
    public void validateDiscountTestFalse4() {
        String discount = "  ";
        boolean actual = validator.validateDiscount(discount);
        assertFalse(actual);
    }

    @Test
    public void validateDiscountTestFalse5() {
        String discount = "<script>";
        boolean actual = validator.validateDiscount(discount);
        assertFalse(actual);
    }

    @Test
    public void validateAverageRatingTestTrue1() {
        String rating = "1";
        boolean actual = validator.validateAverageRating(rating);
        assertTrue(actual);
    }

    @Test
    public void validateAverageRatingTestTrue2() {
        String rating = "10";
        boolean actual = validator.validateAverageRating(rating);
        assertTrue(actual);
    }

    @Test
    public void validateAverageRatingTestFalse1() {
        String rating = "0";
        boolean actual = validator.validateAverageRating(rating);
        assertFalse(actual);
    }

    @Test
    public void validateAverageRatingTestFalse2() {
        String rating = "-1";
        boolean actual = validator.validateAverageRating(rating);
        assertFalse(actual);
    }

    @Test
    public void validateAverageRatingTestFalse3() {
        String rating = "11";
        boolean actual = validator.validateAverageRating(rating);
        assertFalse(actual);
    }

    @Test
    public void validateAverageRatingTestFalse4() {
        String rating = "";
        boolean actual = validator.validateAverageRating(rating);
        assertFalse(actual);
    }

    @Test
    public void validateAverageRatingTestFalse5() {
        String rating = "   ";
        boolean actual = validator.validateAverageRating(rating);
        assertFalse(actual);
    }

    @Test
    public void validateAverageRatingTestFalse6() {
        String rating = "<script>";
        boolean actual = validator.validateAverageRating(rating);
        assertFalse(actual);
    }

    @Test
    public void validateRegistrationPasswordTestTrue1() {
        String firstPassword = "12345Vlad";
        String secondPassword = "12345Vlad";
        boolean actual = validator.validateRegistrationPassword(firstPassword, secondPassword);
        assertTrue(actual);
    }

    @Test
    public void validateRegistrationPasswordTestFalse1() {
        String firstPassword = "12345Vladd";
        String secondPassword = "12345Vlad";
        boolean actual = validator.validateRegistrationPassword(firstPassword, secondPassword);
        assertFalse(actual);
    }

}
