package edu.epam.task6.controller.command;

import edu.epam.task6.controller.command.impl.*;
import edu.epam.task6.controller.command.impl.change.*;
import edu.epam.task6.controller.command.impl.find.*;
import edu.epam.task6.controller.command.impl.page.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static edu.epam.task6.controller.command.CommandType.*;

public class CommandProvider {

    private static final Logger logger = LogManager.getLogger();

    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(TO_ABOUT_US_PAGE, new AboutUsPageCommand());
        commands.put(TO_CATALOG_PAGE, new CatalogPageCommand());
        commands.put(TO_ALL_CATALOG_PAGE, new CatalogAllPageCommand());
        commands.put(TO_LOCKED_CATALOG_PAGE, new CatalogLockedPageCommand());
        commands.put(TO_PROPOSAL_CATALOG_PAGE, new CatalogProposalPageCommand());
        commands.put(TO_HOME_PAGE, new HomePageCommand());
        commands.put(TO_LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(TO_ORDERS_PAGE, new OrdersPageCommand());
        commands.put(TO_CREATE_ORDER_PAGE, new GoToCreateOrderPageCommand());
        commands.put(TO_ACTIVE_ORDERS_PAGE, new OrdersActivePageCommand());
        commands.put(TO_COMPLETED_ORDERS_PAGE, new OrdersCompletedPageCommand());
        commands.put(TO_PROFILE_PAGE, new GoToProfilePageCommand());
        commands.put(TO_PROPOSAL_PAGE, new GoToProposalPageCommand());
        commands.put(TO_REGISTER_PAGE, new GoToRegisterPageCommand());
        commands.put(TO_ALL_USERS_PAGE, new UsersAllPageCommand());
        commands.put(TO_ACTIVE_USERS_PAGE, new UsersActivePageCommand());
        commands.put(TO_BLOCKED_USERS_PAGE, new UsersBlockedPageCommand());
        commands.put(TO_BLOCK_USER_PAGE, new GoToBlockUserPageCommand());
        commands.put(TO_UNBLOCK_USER_PAGE, new GoToUnblockUserPageCommand());
        commands.put(TO_ADD_TATTOO_PAGE, new GoToAddTattooPageCommand());
        commands.put(TO_BLOCK_TATTOO_PAGE, new GoToBlockTattooPageCommand());
        commands.put(TO_UNBLOCK_TATTOO_PAGE, new GoToUnblockTattooPageCommand());
        commands.put(TO_APPROVE_TATTOO_PAGE, new GoToApproveTattooPageCommand());
        commands.put(TO_CODE_PAGE, new GoToCodePageCommand());

        commands.put(TO_FIND_ORDER_BY_ID_PAGE, new FindOrderByIdCommand());
        commands.put(TO_FIND_ORDER_BY_STATUS_PAGE, new FindOrderByStatusCommand());
        commands.put(TO_FIND_ORDER_BY_LOGIN_PAGE, new FindOrderByLoginCommand());

        commands.put(TO_FIND_TATTOO_BY_ID_PAGE, new FindTattooByIdCommand());
        commands.put(TO_FIND_TATTOO_BY_NAME_PAGE, new FindTattooByNameCommand());
        commands.put(TO_FIND_TATTOO_BY_PLACE_PAGE, new FindTattooByPlaceCommand());
        commands.put(TO_FIND_TATTOO_BY_PRICE_RANGE_PAGE, new FindTattooByPriceRangeCommand());

        commands.put(TO_FIND_USER_BY_ID_PAGE, new FindUserByIdCommand());
        commands.put(TO_FIND_USER_BY_LOGIN_PAGE, new FindUserByLoginCommand());
        commands.put(TO_FIND_USERS_BY_NAME_PAGE, new FindUsersByNameCommand());
        commands.put(TO_FIND_USERS_BY_SURNAME_PAGE, new FindUsersBySurnameCommand());

        commands.put(TO_CHANGE_EMAIL_PAGE, new GoToChangeEmailPageCommand());
        commands.put(TO_CHANGE_NAME_PAGE, new GoToChangeNamePageCommand());
        commands.put(TO_CHANGE_SURNAME_PAGE, new GoToChangeSurnamePageCommand());
        commands.put(TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPageCommand());
        commands.put(TO_CHANGE_BALANCE_PAGE, new GoToChangeBalancePageCommand());
        commands.put(TO_CHANGE_USER_STATUS_PAGE, new GoToChangeUserStatusPageCommand());
        commands.put(TO_CHANGE_RATING_PAGE, new GoToChangeRatingPageCommand());
        commands.put(TO_CHANGE_TATTOO_PRICE_PAGE, new GoToChangeTattooPricePageCommand());

        commands.put(CHANGE_EMAIL, new ChangeEmailCommand());
        commands.put(CHANGE_NAME, new ChangeNameCommand());
        commands.put(CHANGE_SURNAME, new ChangeSurnameCommand());
        commands.put(CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(CHANGE_BALANCE, new ChangeBalanceCommand());
        commands.put(CHANGE_USER_STATUS, new ChangeUserStatusCommand());
        commands.put(CHANGE_TATTOO_STATUS, new ChangeTattooStatusCommand());
        commands.put(CHANGE_TATTOO_PRICE, new ChangeTattooPriceCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CHANGE_RATING, new ChangeRatingCommand());

        commands.put(REGISTER_PERSON, new RegisterCommand());
        commands.put(CODE_ENTRY, new CodeEntryCommand());
        commands.put(LOGIN_PERSON, new LoginCommand());
        commands.put(LOGOUT_PERSON, new LogoutCommand());
        commands.put(ADD_TATTOO, new AddTattooCommand());
        commands.put(APPROVE_TATTOO, new ApproveTattooCommand());
        commands.put(CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CREATE_ORDER, new CreateOrderCommand());
        commands.put(COMPLETE_ORDER, new CompleteOrderCommand());
        commands.put(CREATE_COMMENT, new CreateCommentCommand());
        commands.put(DELETE_COMMENT, new DeleteCommentCommand());
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
