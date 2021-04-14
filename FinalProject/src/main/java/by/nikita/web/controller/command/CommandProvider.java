package by.nikita.web.controller.command;

import by.nikita.web.controller.command.impl.*;
import by.nikita.web.controller.command.impl.navigation.*;
import by.nikita.web.controller.command.impl.navigation.GoToHomePage;
import by.nikita.web.controller.command.impl.Logout;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GOTOMAININDEXPAGE, new GoToMainIndexPage());
        commands.put(CommandName.REGISTRATION, new Registration());
        commands.put(CommandName.CREATENEWUSER, new CreateNewUser());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.LOGINATION, new AuthorizationUser());
        commands.put(CommandName.GOTOHOMEPAGE, new GoToHomePage());
        commands.put(CommandName.GOTOBLOCKEDPAGE, new GoToBlockPage());
        commands.put(CommandName.CREATENEWBOOK, new CreateNewBook());
        commands.put(CommandName.GOTOERRORPAGE, new GoToErrorPage());
        commands.put(CommandName.BLOCKUSERCOMMAND, new BlockUserCommand());
        commands.put(CommandName.UNBLOCKUSERCOMMAND, new UnblockUserCommand());
        commands.put(CommandName.DELETEUSERCOMMAND, new DeleteUserCommand());
        commands.put(CommandName.GOTOBOOKPAGE, new GoToBookPage());
        commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPage());
        commands.put(CommandName.GOTOMAINBOOKPAGE, new GoToMainBookPage());
        commands.put(CommandName.BUYBOOKUSER, new BuyBookUser());
        commands.put(CommandName.CREATENEWCOMMENT, new CreateNewComment());
        commands.put(CommandName.DELETEBOOKCOMMAND, new DeleteBookCommand());
        commands.put(CommandName.BLOCKBOOKCOMMAND, new BlockBookCommand());
        commands.put(CommandName.UNBLOCKBOOKCOMMAND, new UnblockBookCommand());
        commands.put(CommandName.UPDATEBALANCECOMMAND, new UpdateBalanceCommand());
        commands.put(CommandName.UPDATEUSERINFOCOMMAND, new UpdateUserInfoCommand());
        commands.put(CommandName.FINDBYNAMECOMMAND, new FindByNameCommand());
        commands.put(CommandName.GOTOVIEWUSERCOMMAND, new GoToViewUsersPage());
        commands.put(CommandName.VIEWALLUSERSCOMMAND, new ViewAllUsersCommand());
        commands.put(CommandName.GOTOLIBRARYPAGE, new GoToLibraryPage());
        commands.put(CommandName.SORTBOOKCOMMAND, new SortBookCommand());
        commands.put(CommandName.READBOOKCOMMAND, new ReadBookCommand());
        commands.put(CommandName.GOTOTEXTPAGE, new GoToTextPage());
        commands.put(CommandName.SEEPROFILECOMMAND, new SeeProfileCommand());
        commands.put(CommandName.REDACTBOOKCOMMAND, new RedactBookCommand());
        commands.put(CommandName.GOTOREDACTBOOKPAGE, new GoToRedactBookPage());
        commands.put(CommandName.UPDATEBOOKINFO, new UpdateBookInfo());
        commands.put(CommandName.GOTOAUTHORPAGE, new GoToAuthorPage());
        commands.put(CommandName.BLOCKCOMMENTCOMMAND, new BlockCommentCommand());
        commands.put(CommandName.UNBLOCKCOMMENTCOMMAND, new UnblockCommentCommand());
        commands.put(CommandName.CHOOSELOCALECOMMAND, new ChooseLocaleCommand());
        commands.put(CommandName.FINDBYGENRECOMMAND, new FindByGenreCommand());
    }


    public Command takeCommand(String name) {
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }


}
