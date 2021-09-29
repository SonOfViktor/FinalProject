package edu.epam.task6.controller.command;

import edu.epam.task6.controller.command.impl.*;
import edu.epam.task6.controller.command.impl.change.*;
import edu.epam.task6.controller.command.impl.find.*;
import edu.epam.task6.controller.command.impl.page.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

public class CommandProvider {

    private static final Logger logger = LogManager.getLogger();

    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.TO_ABOUT_US_PAGE, new GoToAboutUsPageCommand());
        commands.put(CommandType.TO_CATALOG_PAGE, new GoToCatalogPageCommand());
        commands.put(CommandType.TO_ALL_CATALOG_PAGE, new GoToCatalogAllPageCommand());
        commands.put(CommandType.TO_LOCKED_CATALOG_PAGE, new GoToCatalogLockedPageCommand());
        commands.put(CommandType.TO_PROPOSAL_CATALOG_PAGE, new GoToCatalogProposalPageCommand());
        commands.put(CommandType.TO_HOME_PAGE, new GoToHomePageCommand());
        commands.put(CommandType.TO_LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(CommandType.TO_ORDERS_PAGE, new GoToOrdersPageCommand());
        commands.put(CommandType.TO_CREATE_ORDER_PAGE, new GoToCreateOrderPageCommand());
        commands.put(CommandType.TO_ACTIVE_ORDERS_PAGE, new GoToOrdersActivePageCommand());
        commands.put(CommandType.TO_COMPLETED_ORDERS_PAGE, new GoToOrdersCompletedPageCommand());
        commands.put(CommandType.TO_PROFILE_PAGE, new GoToProfilePageCommand());
        commands.put(CommandType.TO_PROPOSAL_PAGE, new GoToProposalPageCommand());
        commands.put(CommandType.TO_REGISTER_PAGE, new GoToRegisterPageCommand());
        commands.put(CommandType.TO_ALL_USERS_PAGE, new GoToUsersAllPageCommand());
        commands.put(CommandType.TO_ACTIVE_USERS_PAGE, new GoToUsersActivePageCommand());
        commands.put(CommandType.TO_BLOCKED_USERS_PAGE, new GoToUsersBlockedPageCommand());
        commands.put(CommandType.TO_BLOCK_USER_PAGE, new GoToBlockUserPageCommand());
        commands.put(CommandType.TO_UNBLOCK_USER_PAGE, new GoToUnblockUserPageCommand());
        commands.put(CommandType.TO_ADD_TATTOO_PAGE, new GoToAddTattooPageCommand());
        commands.put(CommandType.TO_BLOCK_TATTOO_PAGE, new GoToBlockTattooPageCommand());
        commands.put(CommandType.TO_UNBLOCK_TATTOO_PAGE, new GoToUnblockTattooPageCommand());
        commands.put(CommandType.TO_APPROVE_TATTOO_PAGE, new GoToApproveTattooPageCommand());
        commands.put(CommandType.TO_CODE_PAGE, new GoToCodePageCommand());

        commands.put(CommandType.TO_FIND_ORDER_BY_ID_PAGE,
                new GoToFindOrderByIdPageCommand());
        commands.put(CommandType.TO_FIND_ORDER_BY_STATUS_PAGE,
                new GoToFindOrderByStatusPageCommand());
        commands.put(CommandType.TO_FIND_ORDER_BY_LOGIN_PAGE,
                new GoToFindOrderByLoginPageCommand());

        commands.put(CommandType.TO_FIND_TATTOO_BY_ID_PAGE,
                new GoToFindTattooByIdPageCommand());
        commands.put(CommandType.TO_FIND_TATTOO_BY_NAME_PAGE,
                new GoToFindTattooByNamePageCommand());
        commands.put(CommandType.TO_FIND_TATTOO_BY_PLACE_PAGE,
                new GoToFindTattooByPlacePageCommand());
        commands.put(CommandType.TO_FIND_TATTOO_BY_PRICE_RANGE_PAGE,
                new GoToFindTattooByPriceRangePageCommand());

        commands.put(CommandType.TO_FIND_USER_BY_ID_PAGE,
                new GoToFindUserByIdPageCommand());
        commands.put(CommandType.TO_FIND_USER_BY_LOGIN_PAGE,
                new GoToFindUserByLoginPageCommand());
        commands.put(CommandType.TO_FIND_USERS_BY_NAME_PAGE,
                new GoToFindUsersByNamePageCommand());
        commands.put(CommandType.TO_FIND_USERS_BY_SURNAME_PAGE,
                new GoToFindUsersBySurnamePageCommand());

        commands.put(CommandType.TO_CHANGE_EMAIL_PAGE, new GoToChangeEmailPageCommand());
        commands.put(CommandType.TO_CHANGE_NAME_PAGE, new GoToChangeNamePageCommand());
        commands.put(CommandType.TO_CHANGE_SURNAME_PAGE, new GoToChangeSurnamePageCommand());
        commands.put(CommandType.TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPageCommand());
        commands.put(CommandType.TO_CHANGE_BALANCE_PAGE, new GoToChangeBalancePageCommand());
        commands.put(CommandType.TO_CHANGE_USER_STATUS_PAGE, new GoToChangeUserStatusPageCommand());
        commands.put(CommandType.TO_CHANGE_RATING_PAGE, new GoToChangeRatingPageCommand());

        commands.put(CommandType.CHANGE_EMAIL, new ChangeEmailCommand());
        commands.put(CommandType.CHANGE_NAME, new ChangeNameCommand());
        commands.put(CommandType.CHANGE_SURNAME, new ChangeSurnameCommand());
        commands.put(CommandType.CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(CommandType.CHANGE_BALANCE, new ChangeBalanceCommand());
        commands.put(CommandType.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
        commands.put(CommandType.CHANGE_TATTOO_STATUS, new ChangeTattooStatusCommand());
        commands.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandType.CHANGE_RATING, new ChangeRatingCommand());

        commands.put(CommandType.REGISTER_PERSON, new RegisterCommand());
        commands.put(CommandType.CODE_ENTRY, new CodeEntryCommand());
        commands.put(CommandType.LOGIN_PERSON, new LoginCommand());
        commands.put(CommandType.LOGOUT_PERSON, new LogoutCommand());
        commands.put(CommandType.ADD_TATTOO, new AddTattooCommand());
        commands.put(CommandType.APPROVE_TATTOO, new ApproveTattooCommand());
        commands.put(CommandType.CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CommandType.CREATE_ORDER, new CreateOrderCommand());
        commands.put(CommandType.COMPLETE_ORDER, new CompleteOrderCommand());
        commands.put(CommandType.CREATE_COMMENT, new CreateCommentCommand());
        commands.put(CommandType.DELETE_COMMENT, new DeleteCommentCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String command) {
        CommandType commandType;
        if (command != null) {
            try {
                commandType = CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                commandType = CommandType.DEFAULT;
            }
        } else {
            commandType = CommandType.DEFAULT;
        }
        logger.info("Command type: " + commands.get(commandType).toString());
        return commands.get(commandType);
    }
}
