package edu.epam.task6.controller.command;

import edu.epam.task6.controller.command.impl.*;
import edu.epam.task6.controller.command.impl.change.*;
import edu.epam.task6.controller.command.impl.find.*;
import edu.epam.task6.controller.command.impl.page.*;
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
        commands.put(CommandType.TO_ABOUT_US_PAGE_COMMAND, new GoToAboutUsPageCommand());
        commands.put(CommandType.TO_CATALOG_PAGE_COMMAND, new GoToCatalogPageCommand());
        commands.put(CommandType.TO_ALL_CATALOG_PAGE_COMMAND, new GoToCatalogAllPageCommand());
        commands.put(CommandType.TO_LOCKED_CATALOG_PAGE_COMMAND, new GoToCatalogLockedPageCommand());
        commands.put(CommandType.TO_PROPOSAL_CATALOG_PAGE_COMMAND, new GoToCatalogProposalPageCommand());
        commands.put(CommandType.TO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
        commands.put(CommandType.TO_LOGIN_PAGE_COMMAND, new GoToLoginPageCommand());
        commands.put(CommandType.TO_ORDERS_PAGE_COMMAND, new GoToOrdersPageCommand());
        commands.put(CommandType.TO_CREATE_ORDER_PAGE_COMMAND, new GoToCreateOrderPageCommand());
        commands.put(CommandType.TO_ACTIVE_ORDERS_PAGE_COMMAND, new GoToOrdersActivePageCommand());
        commands.put(CommandType.TO_COMPLETED_ORDERS_PAGE_COMMAND, new GoToOrdersCompletedPageCommand());
        commands.put(CommandType.TO_PROFILE_PAGE_COMMAND, new GoToProfilePageCommand());
        commands.put(CommandType.TO_PROPOSAL_PAGE_COMMAND, new GoToProposalPageCommand());
        commands.put(CommandType.TO_REGISTER_PAGE_COMMAND, new GoToRegisterPageCommand());
        commands.put(CommandType.TO_ALL_USERS_PAGE_COMMAND, new GoToUsersAllPageCommand());
        commands.put(CommandType.TO_ACTIVE_USERS_PAGE_COMMAND, new GoToUsersActivePageCommand());
        commands.put(CommandType.TO_BLOCKED_USERS_PAGE_COMMAND, new GoToUsersBlockedPageCommand());
        commands.put(CommandType.TO_BLOCK_USER_PAGE_COMMAND, new GoToBlockUserPageCommand());
        commands.put(CommandType.TO_UNBLOCK_USER_PAGE_COMMAND, new GoToUnblockUserPageCommand());
        commands.put(CommandType.TO_ADD_TATTOO_PAGE_COMMAND, new GoToAddTattooPageCommand());
        commands.put(CommandType.TO_BLOCK_TATTOO_PAGE_COMMAND, new GoToBlockTattooPageCommand());
        commands.put(CommandType.TO_UNBLOCK_TATTOO_PAGE_COMMAND, new GoToUnblockTattooPageCommand());
        commands.put(CommandType.TO_APPROVE_TATTOO_PAGE_COMMAND, new GoToApproveTattooPageCommand());
        commands.put(CommandType.TO_CODE_PAGE_COMMAND, new GoToCodePageCommand());

        commands.put(CommandType.TO_FIND_ORDER_BY_ID_PAGE_COMMAND,
                new GoToFindOrderByIdPageCommand());
        commands.put(CommandType.TO_FIND_ORDER_BY_STATUS_PAGE_COMMAND,
                new GoToFindOrderByStatusPageCommand());
        commands.put(CommandType.TO_FIND_ORDER_BY_LOGIN_PAGE_COMMAND,
                new GoToFindOrderByLoginPageCommand());

        commands.put(CommandType.TO_FIND_TATTOO_BY_ID_PAGE_COMMAND,
                new GoToFindTattooByIdPageCommand());
        commands.put(CommandType.TO_FIND_TATTOO_BY_NAME_PAGE_COMMAND,
                new GoToFindTattooByNamePageCommand());
        commands.put(CommandType.TO_FIND_TATTOO_BY_PLACE_PAGE_COMMAND,
                new GoToFindTattooByPlacePageCommand());
        commands.put(CommandType.TO_FIND_TATTOO_BY_PRICE_RANGE_PAGE_COMMAND,
                new GoToFindTattooByPriceRangePageCommand());

        commands.put(CommandType.TO_FIND_USER_BY_ID_PAGE_COMMAND,
                new GoToFindUserByIdPageCommand());
        commands.put(CommandType.TO_FIND_USER_BY_LOGIN_PAGE_COMMAND,
                new GoToFindUserByLoginPageCommand());
        commands.put(CommandType.TO_FIND_USERS_BY_NAME_PAGE_COMMAND,
                new GoToFindUsersByNamePageCommand());
        commands.put(CommandType.TO_FIND_USERS_BY_SURNAME_PAGE_COMMAND,
                new GoToFindUsersBySurnamePageCommand());

        commands.put(CommandType.TO_CHANGE_EMAIL_PAGE_COMMAND, new GoToChangeEmailPageCommand());
        commands.put(CommandType.TO_CHANGE_NAME_PAGE_COMMAND, new GoToChangeNamePageCommand());
        commands.put(CommandType.TO_CHANGE_SURNAME_PAGE_COMMAND, new GoToChangeSurnamePageCommand());
        commands.put(CommandType.TO_CHANGE_PASSWORD_PAGE_COMMAND, new GoToChangePasswordPageCommand());
        commands.put(CommandType.TO_CHANGE_BALANCE_PAGE_COMMAND, new GoToChangeBalancePageCommand());
        commands.put(CommandType.TO_CHANGE_USER_STATUS_PAGE_COMMAND, new GoToChangeUserStatusPageCommand());

        commands.put(CommandType.CHANGE_EMAIL_COMMAND, new ChangeEmailCommand());
        commands.put(CommandType.CHANGE_NAME_COMMAND, new ChangeNameCommand());
        commands.put(CommandType.CHANGE_SURNAME_COMMAND, new ChangeSurnameCommand());
        commands.put(CommandType.CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
        commands.put(CommandType.CHANGE_BALANCE_COMMAND, new ChangeBalanceCommand());
        commands.put(CommandType.CHANGE_USER_STATUS_COMMAND, new ChangeUserStatusCommand());
        commands.put(CommandType.CHANGE_TATTOO_STATUS_COMMAND, new ChangeTattooStatusCommand());
        commands.put(CommandType.CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand());

        commands.put(CommandType.REGISTER_PERSON_COMMAND, new RegisterCommand());
        commands.put(CommandType.CODE_ENTRY_COMMAND, new CodeEntryCommand());
        commands.put(CommandType.LOGIN_PERSON_COMMAND, new LoginCommand());
        commands.put(CommandType.LOGOUT_PERSON_COMMAND, new LogoutCommand());
        commands.put(CommandType.ADD_TATTOO_COMMAND, new AddTattooCommand());
        commands.put(CommandType.APPROVE_TATTOO_COMMAND, new ApproveTattooCommand());
        commands.put(CommandType.CANCEL_ORDER_COMMAND, new CancelOrderCommand());
        commands.put(CommandType.CREATE_ORDER_COMMAND, new CreateOrderCommand());
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
