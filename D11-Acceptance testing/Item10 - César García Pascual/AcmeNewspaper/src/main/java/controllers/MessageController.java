
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import utilities.internal.SchemaPrinter;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("profile/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService	messageService;
	@Autowired
	private ActorService	actorService;
	@Autowired
	private FolderService	folderService;


	public MessageController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false) final Integer folderId, @RequestParam(required = false) final Integer messageId) {
		ModelAndView result;
		try {
			Actor a = actorService.findByPrincipal();
			result = new ModelAndView("message/list");
			List<Folder> folders = new ArrayList<Folder>(a.getFolders());
			List<Folder> lvl1Folders = new ArrayList<Folder>(folderService.getLvl1FoldersFrom(a));

			Folder actualFolder;
			if (folderId == null)
				actualFolder = folderService.findInboxFrom(a.getId());
			else {
				actualFolder = folderService.findOne(folderId);
				if (!folderService.isMine(actualFolder))
					return new ModelAndView("redirect:list.do");
			}
			if (actualFolder.getSystem())
				folders = lvl1Folders;
			else
				folders = (List<Folder>) actualFolder.getFolders();

			result.addObject("systemFolders", folderService.getSystemFoldersFrom(a));
			result.addObject("folders", folders);
			result.addObject("actualFolder", actualFolder);
			result.addObject("lvl1Folders", lvl1Folders);

			if (messageId != null) {
				Message showMessage = messageService.findOne(messageId);
				if (showMessage.getActorFrom() == a || showMessage.getActorTo() == a)
					result.addObject("showMessage", showMessage);
			}

			result.addObject("messages", actualFolder.getMessages());
			result.addObject("newMessage", messageService.create());
			result.addObject("newFolder", folderService.create());
			result.addObject("actorsTo", actorService.findAll());
			result.addObject("requestUri", "profile/message/list.do");
			return result;
		} catch (Throwable oops) {
			return new ModelAndView("redirect:/");
		}
	}
	@RequestMapping("/send")
	public ModelAndView newMessage(@Valid Message newMessage, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()){
			SchemaPrinter.print(binding.getAllErrors());
			result = new ModelAndView("redirect:list.do");
		}else
			try {
				messageService.save(newMessage);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = new ModelAndView("redirect:list.do");
			}
		return result;
	}

	@RequestMapping("/newFolder")
	public ModelAndView newFolder(@Valid Folder newFolder, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = new ModelAndView("redirect:list.do");
		else
			try {
				folderService.save(newFolder, actorService.findByPrincipal());
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:list.do");
			}
		return result;
	}

	@RequestMapping("/editFolder")
	public ModelAndView editFolder(@RequestParam(required = true) final Integer folderId, @RequestParam(required = true) final String folderName) {
		ModelAndView result;
		try {
			folderService.editName(folderId, folderName);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping("/deleteFolder")
	public ModelAndView deleteFolder(@RequestParam(required = true) final Integer folderId) {
		ModelAndView result;
		try {
			folderService.delete(folderService.findOne(folderId));
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping("/moveMessage")
	public ModelAndView moveMessage(@RequestParam(required = true) final Integer messageId, @RequestParam(required = true) final Integer folderId) {
		ModelAndView result;
		try {
			messageService.move(messageService.findOne(messageId), folderService.findOne(folderId));
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping("/deleteMessage")
	public ModelAndView deleteMessage(@RequestParam(required = true) final Integer messageId) {
		ModelAndView result;
		try {
			messageService.delete(messageService.findOne(messageId));
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

}
