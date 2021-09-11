package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeBalanceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = new UserServiceImpl();
        try {
            Map<String, String> parameters = new HashMap<>();

            Optional<User> userBase = userService.findById(userId);
            if (userBase.isPresent()) {
                BigDecimal balance = BigDecimal.valueOf(
                        Long.valueOf(request.getParameter(RequestParameter.USER_BALANCE)));
                balance = balance.add(userBase.get().getBalance());
                parameters.put(ColumnName.USER_BALANCE, balance.toString());
                userService.updateBalance(parameters, userId);

                user.setBalance(balance);
                session.setAttribute(SessionAttribute.USER, user);

                router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
                logger.info("Balance updated successfully.");
            } else {
                logger.error("Error during finding user by id from session: ");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing balance of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}