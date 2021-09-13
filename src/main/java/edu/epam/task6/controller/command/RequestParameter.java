package edu.epam.task6.controller.command;

public final class RequestParameter {

    public static final String USER_ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_REPEAT_PASSWORD = "repeat_password";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_REGISTRATION_DATE = "registration_date";
    public static final String USER_DISCOUNT = "discount";
    public static final String USER_BALANCE = "balance";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";
    public static final String USER_BUTTON = "active";

    public static final String ORDER_ID = "id";
    public static final String ORDER_PAID = "paid";
    public static final String ORDER_REGISTRATION_DATE = "registration_date";
    public static final String ORDER_STATUS = "order_status";
    public static final String ORDER_TATTOO_ID = "tattoo_id";

    public static final String TATTOO_ID = "id";
    public static final String TATTOO_NAME = "name";
    public static final String TATTOO_DESCRIPTION = "description";
    public static final String TATTOO_WIDTH = "width";
    public static final String TATTOO_HEIGHT = "height";
    public static final String TATTOO_IMAGE_URL = "image_url";
    public static final String TATTOO_PLACE = "place";
    public static final String TATTOO_PRICE = "price";
    public static final String TATTOO_MIN_PRICE = "min_price";
    public static final String TATTOO_MAX_PRICE = "max_price";
    public static final String TATTOO_STATUS = "tattoo_status";
    public static final String TATTOO_BUTTON = "active";

    public static final String WHAT_CHANGE = "what_change";
    public static final String PASSWORD_OLD = "password_old";
    public static final String PASSWORD_NEW = "password_new";

    public static final String COMMAND = "command";
    public static final String CATALOG = "catalog";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String ORDER = "order";
    public static final String ORDERS = "orders";
    public static final String TATTOO = "tattoo";
    public static final String PROFILE = "profile";

    public static final String COMMENTS = "comments";
    public static final String RATING = "rating";
    public static final String USER_RATING = "user_rating";
    public static final String TATTOO_RATING = "tattoo_rating";

    public static final String REGISTER_CODE = "register_code";
    public static final String GENERATE_CODE = "generate_code";

    public static final String COMMENT_ID ="comment_id";
    public static final String COMMENT_TEXT ="text";
    public static final String COMMENT_REGISTRATION_DATE ="registration_date";
    public static final String COMMENT_USER_ID ="users_user_id";

    public static final String PAGES_NUMBER = "pages_number";
    public static final String ELEMENTS_PER_PAGE = "elements_per_page";
    public static final String NUMBER_OF_TATTOOS = "number_of_tattoos";
    public static final String NUMBER_OF_USERS = "number_of_users";
    public static final String NUMBER_OF_ORDERS = "number_of_orders";
    public static final String TITLE_USERS = "title_users";
    public static final String TITLE_ORDERS = "title_orders";
    public static final String TITLE_TATTOOS = "title_tattoos";
    public static final String TITLE_USERS_ALL = "all";
    public static final String TITLE_USERS_ACTIVE = "active";
    public static final String TITLE_USERS_BLOCKED = "blocked";
    public static final String TITLE_USERS_FOUNDED = "founded";
    public static final String TITLE_ORDERS_ALL = "all";
    public static final String TITLE_ORDERS_ACTIVE = "active";
    public static final String TITLE_ORDERS_COMPLETED = "completed";
    public static final String TITLE_ORDERS_FOUNDED = "founded";
    public static final String TITLE_ORDERS_PERSON = "person";
    public static final String TITLE_TATTOOS_ALL = "all";
    public static final String TITLE_TATTOOS_ACTIVE = "active";
    public static final String TITLE_TATTOOS_LOCKED = "locked";
    public static final String TITLE_TATTOOS_FOUNDED = "founded";
    public static final String TITLE_TATTOOS_PROPOSAL = "proposal";

    public static final String LOGIN_ERROR = "login_error";
    public static final String USER_BLOCKED_ERROR = "user_blocked_error";
    public static final String FIND_USER_ERROR = "find_user_error";
    public static final String FIND_ORDER_ERROR = "find_order_error";
    public static final String FIND_TATTOO_ERROR = "find_tattoo_error";
    public static final String CHANGE_PASSWORD_ERROR = "change_password_error";
    public static final String REPEAT_PASSWORD_ERROR = "repeat_password_error";
    public static final String ENTERED_CODE_ERROR = "entered_code_error";
    public static final String BALANCE_NOT_ENOUGH_ERROR_MESSAGE = "balance_not_enough_error_message";
    public static final String TATTOO_ID_NOT_FOUND_MESSAGE = "tattoo_id_not_found_message";
    public static final String EMPTY_ORDERS_MESSAGE = "empty_orders_message";

    private RequestParameter() {}
}
