package edu.epam.task6.controller.command;

public final class PagePath {
    public static final String MAIN_PAGE = "assets/jsp/main.jsp";
    public static final String MAIN_PAGE_REDIRECT = "/ProjectServlet?command=to_home_page";

    public static final String CATALOG_PAGE = "assets/jsp/catalog.jsp";
    public static final String CATALOG_PAGE_REDIRECT = "/ProjectServlet?command=to_catalog_page";
    public static final String CATALOG_PAGE_ALL_REDIRECT = "/ProjectServlet?command=to_all_catalog_page";
    public static final String CATALOG_PAGE_LOCKED_REDIRECT = "/ProjectServlet?command=to_locked_catalog_page";
    public static final String CATALOG_PAGE_PROPOSAL_REDIRECT = "/ProjectServlet?command=to_proposal_catalog_page";

    public static final String USERS_PAGE = "assets/jsp/users.jsp";
    public static final String USERS_PAGE_ALL_REDIRECT = "/ProjectServlet?command=to_all_users_page";
    public static final String USERS_PAGE_ACTIVE_REDIRECT = "/ProjectServlet?command=to_active_users_page";
    public static final String USERS_PAGE_BLOCKED_REDIRECT = "/ProjectServlet?command=to_blocked_users_page";

    public static final String ORDERS_PAGE = "assets/jsp/orders.jsp";
    public static final String ORDERS_PAGE_ALL_REDIRECT = "/ProjectServlet?command=to_orders_page";
    public static final String ORDERS_PAGE_ACTIVE_REDIRECT = "/ProjectServlet?command=to_active_orders_page";
    public static final String ORDERS_PAGE_COMPLETED_REDIRECT = "/ProjectServlet?command=to_completed_orders_page";

    public static final String CREATE_ORDER_PAGE = "assets/jsp/create_order.jsp";
    public static final String CREATE_ORDER_PAGE_REDIRECT = "/ProjectServlet?command=to_create_order_page";

    public static final String LOGIN_PAGE = "assets/jsp/login.jsp";
    public static final String LOGIN_PAGE_REDIRECT = "/ProjectServlet?command=to_login_page";

    public static final String PROFILE_PAGE_USER = "assets/jsp/profile.jsp";
    public static final String PROFILE_PAGE_ADMIN = "assets/jsp/profile_admin.jsp";
    public static final String PROFILE_PAGE_REDIRECT = "/ProjectServlet?command=to_profile_page";

    public static final String ADD_TATTOO_PAGE = "assets/jsp/add_tattoo.jsp";
    public static final String ADD_TATTOO_PAGE_REDIRECT = "/ProjectServlet?command=to_add_tattoo_page";

    public static final String APPROVE_TATTOO_PAGE = "assets/jsp/approve_tattoo.jsp";

    public static final String PROPOSAL_PAGE = "assets/jsp/proposal.jsp";
    public static final String PROPOSAL_PAGE_REDIRECT = "/ProjectServlet?command=to_proposal_page";

    public static final String REGISTER_PAGE = "assets/jsp/register.jsp";
    public static final String REGISTER_PAGE_REDIRECT = "/ProjectServlet?command=to_register_page";

    public static final String CODE_PAGE = "assets/jsp/code.jsp";
    public static final String CODE_PAGE_REDIRECT = "/ProjectServlet?command=to_code_page";

    public static final String ABOUT_US_PAGE = "assets/jsp/about_us.jsp";
    public static final String ABOUT_US_PAGE_REDIRECT = "/ProjectServlet?command=to_about_us_page";

    public static final String FIND_OPTIONAL_PAGE = "assets/jsp/find_optional.jsp";

    public static final String ERROR_PAGE_500 = "assets/jsp/error500.jsp";

    public static final String CHANGE_PAGE = "assets/jsp/change.jsp";
    public static final String CHANGE_PAGE_EMAIL_REDIRECT = "/ProjectServlet?command=to_change_email_page";
    public static final String CHANGE_PAGE_PASSWORD_REDIRECT = "/ProjectServlet?command=to_change_password_page";
    public static final String CHANGE_PAGE_BALANCE_REDIRECT = "/ProjectServlet?command=to_change_balance_page";
    public static final String CHANGE_PAGE_NAME_REDIRECT = "/ProjectServlet?command=to_change_name_page";
    public static final String CHANGE_PAGE_SURNAME_REDIRECT = "/ProjectServlet?command=to_change_surname_page";
    public static final String CHANGE_PAGE_USER_STATUS_REDIRECT = "/ProjectServlet?command=to_change_user_status_page";

    public static final String CHANGE_BLOCK_TATTOO_PAGE_REDIRECT = "/ProjectServlet?command=to_block_tattoo_page";
    public static final String CHANGE_BLOCK_USER_PAGE_REDIRECT = "/ProjectServlet?command=to_block_user_page";
    public static final String CHANGE_UNBLOCK_TATTOO_PAGE_REDIRECT = "/ProjectServlet?command=to_unblock_tattoo_page";
    public static final String CHANGE_UNBLOCK_USER_PAGE_REDIRECT = "/ProjectServlet?command=to_unblock_user_page";

    private PagePath() {}
}
