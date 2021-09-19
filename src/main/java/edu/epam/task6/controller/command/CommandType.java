package edu.epam.task6.controller.command;

import edu.epam.task6.model.entity.UserRole;

import java.util.List;

import static edu.epam.task6.model.entity.UserRole.ADMIN;
import static edu.epam.task6.model.entity.UserRole.USER;
import static edu.epam.task6.model.entity.UserRole.VISITOR;

public enum CommandType {
    DEFAULT (ADMIN, USER, VISITOR),
    TO_ABOUT_US_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_CATALOG_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_ALL_CATALOG_PAGE_COMMAND (ADMIN),
    TO_LOCKED_CATALOG_PAGE_COMMAND (ADMIN),
    TO_PROPOSAL_CATALOG_PAGE_COMMAND (ADMIN),
    TO_HOME_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_LOGIN_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_ORDERS_PAGE_COMMAND (ADMIN, USER),
    TO_CREATE_ORDER_PAGE_COMMAND (ADMIN, USER),
    TO_ACTIVE_ORDERS_PAGE_COMMAND (ADMIN),
    TO_COMPLETED_ORDERS_PAGE_COMMAND (ADMIN),
    TO_PROFILE_PAGE_COMMAND (ADMIN, USER),
    TO_PROPOSAL_PAGE_COMMAND (ADMIN, USER),
    TO_REGISTER_PAGE_COMMAND(ADMIN, USER, VISITOR),
    TO_ALL_USERS_PAGE_COMMAND (ADMIN),
    TO_ACTIVE_USERS_PAGE_COMMAND (ADMIN),
    TO_BLOCKED_USERS_PAGE_COMMAND (ADMIN),
    TO_BLOCK_USER_PAGE_COMMAND (ADMIN),
    TO_UNBLOCK_USER_PAGE_COMMAND (ADMIN),
    TO_ADD_TATTOO_PAGE_COMMAND (ADMIN),
    TO_BLOCK_TATTOO_PAGE_COMMAND (ADMIN),
    TO_UNBLOCK_TATTOO_PAGE_COMMAND (ADMIN),
    TO_APPROVE_TATTOO_PAGE_COMMAND (ADMIN),
    TO_CODE_PAGE_COMMAND (ADMIN, USER, VISITOR),

    TO_FIND_ORDER_BY_ID_PAGE_COMMAND (ADMIN, USER),
    TO_FIND_ORDER_BY_STATUS_PAGE_COMMAND (ADMIN, USER),
    TO_FIND_ORDER_BY_LOGIN_PAGE_COMMAND (ADMIN),

    TO_FIND_USER_BY_ID_PAGE_COMMAND (ADMIN),
    TO_FIND_USER_BY_LOGIN_PAGE_COMMAND (ADMIN),
    TO_FIND_USERS_BY_NAME_PAGE_COMMAND (ADMIN),
    TO_FIND_USERS_BY_SURNAME_PAGE_COMMAND (ADMIN),

    TO_FIND_TATTOO_BY_ID_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_FIND_TATTOO_BY_NAME_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_FIND_TATTOO_BY_PLACE_PAGE_COMMAND (ADMIN, USER, VISITOR),
    TO_FIND_TATTOO_BY_PRICE_RANGE_PAGE_COMMAND (ADMIN, USER, VISITOR),

    TO_CHANGE_EMAIL_PAGE_COMMAND (ADMIN, USER),
    TO_CHANGE_PASSWORD_PAGE_COMMAND (ADMIN, USER),
    TO_CHANGE_NAME_PAGE_COMMAND (ADMIN, USER),
    TO_CHANGE_SURNAME_PAGE_COMMAND (ADMIN, USER),
    TO_CHANGE_BALANCE_PAGE_COMMAND (USER),
    TO_CHANGE_USER_STATUS_PAGE_COMMAND (ADMIN),
    TO_CHANGE_RATING_PAGE_COMMAND (USER),

    CHANGE_EMAIL_COMMAND (ADMIN, USER),
    CHANGE_NAME_COMMAND (ADMIN, USER),
    CHANGE_SURNAME_COMMAND (ADMIN, USER),
    CHANGE_PASSWORD_COMMAND (ADMIN, USER),
    CHANGE_BALANCE_COMMAND (USER),
    CHANGE_USER_STATUS_COMMAND (ADMIN),
    CHANGE_TATTOO_STATUS_COMMAND (ADMIN),
    CHANGE_LANGUAGE_COMMAND (ADMIN, USER, VISITOR),
    CHANGE_RATING_COMMAND (USER),

    REGISTER_PERSON_COMMAND (VISITOR),
    CODE_ENTRY_COMMAND (VISITOR),
    LOGIN_PERSON_COMMAND (ADMIN, USER, VISITOR),
    LOGOUT_PERSON_COMMAND (ADMIN, USER),
    ADD_TATTOO_COMMAND (ADMIN, USER),
    APPROVE_TATTOO_COMMAND (ADMIN),
    CANCEL_ORDER_COMMAND (ADMIN, USER),
    CREATE_ORDER_COMMAND (ADMIN, USER),
    COMPLETE_ORDER_COMMAND(ADMIN),
    CREATE_COMMENT_COMMAND (ADMIN, USER),
    DELETE_COMMENT_COMMAND (ADMIN);

    private List<UserRole> roles;

    private CommandType(UserRole... roles) {
        this.roles = List.of(roles);
    }

    public boolean isContainRole(UserRole userRole) {
        return roles.stream().anyMatch(role -> role == userRole);
    }
}
