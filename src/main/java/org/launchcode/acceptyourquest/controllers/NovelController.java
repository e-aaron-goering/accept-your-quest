package org.launchcode.acceptyourquest.controllers;

import org.launchcode.acceptyourquest.models.*;
import org.launchcode.acceptyourquest.models.data.ChoiceDao;
import org.launchcode.acceptyourquest.models.data.CustomOptionDao;
import org.launchcode.acceptyourquest.models.data.NovelDao;
import org.launchcode.acceptyourquest.models.data.PageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Controller
@RequestMapping("novel")
public class NovelController {

    @Autowired
    private NovelDao novelDao;

    @Autowired
    private PageDao pageDao;

    @Autowired
    private ChoiceDao choiceDao;

    @Autowired
    private CustomOptionDao customOptionDao;

    // Request path: /novel
    @RequestMapping(value = "")
    public String index(Model model) {

        //build novels
        if(getRecentlyUpdated().isEmpty()) {
            Novel novelOne = new Novel("The Dragon's Champion", "Pulled into a Realm of Magic and Dragons in order to save it from destruction");
            novelOne.setFnlnpn(true);
            novelOne.setPublished(true);
            novelDao.save(novelOne);

            Page pageOne = new Page("Prologue", fileInput("DC-1.txt"));
            pageDao.save(pageOne);
            novelOne.addPage(pageOne);

            Choice choiceOne = new Choice("Back away slowly.", pageOne);
            choiceDao.save(choiceOne);
            novelOne.getPage(0).addChoice(choiceOne);

            Choice choiceTwo = new Choice("Step into the portal.", pageOne);
            choiceDao.save(choiceTwo);
            novelOne.getPage(0).addChoice(choiceTwo);

            Page pageTwo = new Page(choiceOne.getChoiceDescription(), fileInput("DC-2.txt"));
            pageDao.save(pageTwo);
            novelOne.addPage(pageTwo);
            choiceOne.setPagePointer(pageTwo.getId());

            Page pageThree = new Page(choiceTwo.getChoiceDescription(), fileInput("DC-3.txt"));
            pageDao.save(pageThree);
            novelOne.addPage(pageThree);
            Choice choiceThree = new Choice("Continue Reading", pageThree);
            choiceDao.save(choiceThree);
            novelOne.getPage(2).addChoice(choiceThree);
            choiceTwo.setPagePointer(pageThree.getId());

            Page pageFour = new Page(choiceThree.getChoiceDescription(), fileInput("DC-4.txt"));
            pageDao.save(pageFour);
            novelOne.addPage(pageFour);
            choiceThree.setPagePointer(pageFour.getId());

            novelDao.save(novelOne);
            pageDao.save(pageOne);
            pageDao.save(pageTwo);
            pageDao.save(pageThree);
            pageDao.save(pageFour);
            choiceDao.save(choiceOne);
            choiceDao.save(choiceTwo);
            choiceDao.save(choiceThree);

            Novel novelTwo = new Novel("Legend of the Radiant Ranger", "You've been chosen to wield Super Powers.  Will you use them for Good or Evil?");
            novelTwo.setPublished(true);
            novelTwo.setFnlnpn(true);
            novelDao.save(novelTwo);

            CustomOption customOptionOne = new CustomOption("what is your favorite color:", "#fc#");
            customOptionOne.setNovel(novelTwo);
            customOptionDao.save(customOptionOne);

            CustomOption customOptionTwo = new CustomOption("name a color that goes well with your favorite:", "#sc#");
            customOptionTwo.setNovel(novelTwo);
            customOptionDao.save(customOptionTwo);

            Page pageFive = new Page("Prologue", fileInput("RR-1.txt"));
            pageDao.save(pageFive);
            novelTwo.addPage(pageFive);

            Choice choiceFour = new Choice("Continue Reading", pageFive);
            choiceDao.save(choiceFour);

            Page pageSix = new Page(choiceFour.getChoiceDescription(), fileInput("RR-2.txt"));
            pageDao.save(pageSix);
            novelTwo.getPage(0).addChoice(choiceFour);
            novelTwo.addPage(pageSix);
            choiceFour.setPagePointer(pageSix.getId());

            Novel novelThree = new Novel("Sentient Super-Car", "You win a car in a sweepstakes.  It just happens to have a few extra features.");
            novelThree.setPublished(true);
            novelDao.save(novelThree);

            Page pageSeven = new Page("Prologue", fileInput("SS-1.txt"));
            pageDao.save(pageSeven);
            novelThree.addPage(pageSeven);

            Choice choiceFive = new Choice("Continue Reading", pageSeven);
            choiceDao.save(choiceFive);

            Page pageEight = new Page(choiceFive.getChoiceDescription(), fileInput("SS-2.txt"));
            pageDao.save(pageEight);
            novelThree.getPage(0).addChoice(choiceFive);
            novelThree.addPage(pageEight);
            choiceFive.setPagePointer(pageEight.getId());

            CustomOption customOptionThree = new CustomOption("what is your favorite color of car:", "#cc#");
            customOptionThree.setNovel(novelThree);
            customOptionDao.save(customOptionThree);

            CustomOption customOptionFour = new CustomOption("what is your favorite make/model of car:", "#cm#");
            customOptionFour.setNovel(novelThree);
            customOptionDao.save(customOptionFour);

            Novel novelFour = new Novel("Arizona Lewis and the Adventure of the Atlantean Artifact", "You choose the destiny of a world renowned adventurer in a tale of international intrigue");
            novelFour.setPublished(true);

            Page pageNine = new Page("Prologue", fileInput("AL-1.txt"));
            pageDao.save(pageNine);
            novelFour.addPage(pageNine);

            novelDao.save(novelTwo);
            pageDao.save(pageFive);
            pageDao.save(pageSix);
            choiceDao.save(choiceFour);
            novelDao.save(novelThree);
            pageDao.save(pageSeven);
            pageDao.save(pageEight);
            choiceDao.save(choiceFive);
            novelDao.save(novelFour);
            pageDao.save(pageNine);
        }


        List<Novel> recentlyUpdated = getRecentlyUpdated();

        model.addAttribute("recent", recentlyUpdated);
        model.addAttribute("title", "accept your quest");

        return "novel/index";
    }

    // Request path: /novel/title-page/{novelId}
    @RequestMapping(value = "title-page/{novelId}", method = RequestMethod.GET)
    public String titlePage(Model model, @PathVariable int novelId) {

        model.addAttribute("novel", novelDao.findById(novelId).get());
        model.addAttribute("title", "accept your quest");

        return "novel/title-page";
    }

    // Request path: /novel/story-options/{novelId}
    @RequestMapping(value = "story-options/{novelId}", method = RequestMethod.GET)
    public String storyOptions(Model model, @PathVariable int novelId) {

        Novel novel = novelDao.findById(novelId).get();

        //load story-options-bravo.html as the story contains only the first name/last name/pronouns options
        if((novel.isFnlnpn()) && (novel.getCustomOptions().isEmpty())) {

            model.addAttribute("novel", novelDao.findById(novelId).get());
            model.addAttribute("novelId", novelId);
            model.addAttribute("pronouns", Pronouns.values());
            model.addAttribute("title", "story options");

            return "novel/story-options-bravo";
        }
        //load story-options-charlie.html as the story contains only custom options
        if(!(novel.isFnlnpn()) && (!(novel.getCustomOptions().isEmpty()))) {

            model.addAttribute("novel", novelDao.findById(novelId).get());
            model.addAttribute("novelId", novelId);
            model.addAttribute("title", "story options");
            model.addAttribute("storyOptions", novelDao.findById(novelId).get().getCustomOptions());

            return "novel/story-options-charlie";
        }

        //load story-options-delta.html as the story contains both the FN/LN/PN options as well as custom options
        if((novel.isFnlnpn()) && (!(novel.getCustomOptions().isEmpty()))) {
            model.addAttribute("novel", novelDao.findById(novelId).get());
            model.addAttribute("novelId", novelId);
            model.addAttribute("storyOptions", novelDao.findById(novelId).get().getCustomOptions());
            model.addAttribute("pronouns", Pronouns.values());
            model.addAttribute("title", "story options");

            return "novel/story-options-delta";
        }

        //load story-options-alpha.html as the story contains no options
        model.addAttribute("novel", novelDao.findById(novelId).get());
        model.addAttribute("novelId", novelId);
        model.addAttribute("title", "story options");

        return "novel/story-options-alpha";

    }

    @RequestMapping(value = "alpha/{novelId}/{pageId}", method = RequestMethod.POST)
    public String pageAlphaDisplay(Model model, @PathVariable int novelId, @PathVariable int pageId) {

        String pageText = pageDao.findById(pageId).get().getPageText();

        model.addAttribute("pageText", pageText);
        model.addAttribute("title", novelDao.findById(novelId).get().getTitle());
        model.addAttribute("novelId", novelId);
        model.addAttribute("pageId", pageId);
        model.addAttribute("choices", pageDao.findById(pageId).get().getChoices());
        model.addAttribute("subTitle", pageDao.findById(pageId).get().getPageTitle());

        return "novel/page-alpha";
    }

    @RequestMapping(value = "bravo/{novelId}/{pageId}", method = RequestMethod.POST)
    public String pageBravoDisplay(Model model, @PathVariable int novelId, @PathVariable int pageId, @RequestParam String firstName,
                              @RequestParam String lastName, @RequestParam String pronoun) {

        String pageText = pageDao.findById(pageId).get().getPageText();
        String textHolder = pageText;

        pageText = textHolder.replaceAll("#fn#", firstName);
        textHolder = pageText;

        pageText = textHolder.replaceAll("#ln#", lastName);

        model.addAttribute("pageText", pageText);
        model.addAttribute("title", novelDao.findById(novelId).get().getTitle());
        model.addAttribute("novelId", novelId);
        model.addAttribute("pageId", pageId);
        model.addAttribute("choices", pageDao.findById(pageId).get().getChoices());
        model.addAttribute("subTitle", pageDao.findById(pageId).get().getPageTitle());


        //passing the story options back into the next page
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("pronoun", pronoun);

        return "novel/page-bravo";
    }

    @RequestMapping(value = "charlie/{novelId}/{pageId}", method = RequestMethod.POST)
    public String pageCharlieDisplay(Model model, @PathVariable int novelId, @PathVariable int pageId, @RequestParam String[] storyOptions) {

        Novel novel = novelDao.findById(novelId).get();
        String pageText = pageDao.findById(pageId).get().getPageText();
        String textHolder = pageText;

        for (int i = 0; i < storyOptions.length; i++) {
            String tag = novel.getCustomOptions().get(i).getCustomTag();
            String option = storyOptions[i];

            pageText = textHolder.replaceAll(tag, option);
            textHolder = pageText;
        }

        model.addAttribute("pageText", pageText);
        model.addAttribute("title", novelDao.findById(novelId).get().getTitle());
        model.addAttribute("novelId", novelId);
        model.addAttribute("pageId", pageId);
        model.addAttribute("choices", pageDao.findById(pageId).get().getChoices());
        model.addAttribute("subTitle", pageDao.findById(pageId).get().getPageTitle());


        //passing the story options back into the next page
        model.addAttribute("storyOptions", storyOptions);

        return "novel/page-charlie";
    }

    @RequestMapping(value = "delta/{novelId}/{pageId}", method = RequestMethod.POST)
    public String pageDeltaDisplay(Model model, @PathVariable int novelId, @PathVariable int pageId, @RequestParam String firstName,
                                   @RequestParam String lastName, @RequestParam String pronoun, @RequestParam String[] storyOptions) {

        Novel novel = novelDao.findById(novelId).get();
        String pageText = pageDao.findById(pageId).get().getPageText();

        String textHolder = pageText;

        pageText = textHolder.replaceAll("#fn#", firstName);
        textHolder = pageText;

        pageText = textHolder.replaceAll("#ln#", lastName);

        for (int i = 0; i < storyOptions.length; i++) {
            String tag = novel.getCustomOptions().get(i).getCustomTag();
            String option = storyOptions[i];

            pageText = textHolder.replaceAll(tag, option);
            textHolder = pageText;
        }

        model.addAttribute("pageText", pageText);
        model.addAttribute("title", novelDao.findById(novelId).get().getTitle());
        model.addAttribute("novelId", novelId);
        model.addAttribute("pageId", pageId);
        model.addAttribute("choices", pageDao.findById(pageId).get().getChoices());
        model.addAttribute("subTitle", pageDao.findById(pageId).get().getPageTitle());


        //passing the story options back into the next page
        model.addAttribute("storyOptions", storyOptions);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("pronoun", pronoun);
        return "novel/page-delta";
    }

    public ArrayList<Novel> getRecentlyUpdated(){
        Iterable<Novel> novelsI = novelDao.findAll();
        ArrayList<Novel> novelsAL = new ArrayList<>();

        for (Novel n : novelsI)
        {
            novelsAL.add(n);
        }
           return novelsAL;
    }


    public String fileInput(String fileName) {

        StringBuilder sb = new StringBuilder();
        try
        {
            Resource resource = new ClassPathResource(fileName);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            Scanner sc = new Scanner(reader);

            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append("\n");
            }
            sc.close();
        }catch(IOException e) {
            System.out.println("Failed to load file data");
            e.printStackTrace();
        }
        return sb.toString();
    }
}

