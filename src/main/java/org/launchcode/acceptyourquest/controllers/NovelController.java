package org.launchcode.acceptyourquest.controllers;

//import org.launchcode.acceptyourquest.models.Novel;
import org.launchcode.acceptyourquest.models.ChoiceTemp;
import org.launchcode.acceptyourquest.models.NovelTemp;
//import org.launchcode.acceptyourquest.models.Page;
//import org.launchcode.acceptyourquest.models.User;
import org.launchcode.acceptyourquest.models.PageTemp;
import org.launchcode.acceptyourquest.models.Pronouns;
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

    public ArrayList<NovelTemp> novels = new ArrayList<>();

    // Request path: /novel
    @RequestMapping(value = "")
    public String index(Model model) {

        //build novels
        if((novels.isEmpty())) {
            novels.add(new NovelTemp("The Dragon's Champion", "Pulled into a Realm of Magic and Dragons in order to save it from destruction"));
            novels.add(new NovelTemp("Legend of the Radiant Ranger", "You've been chosen to wield Super Powers.  Will you use them for Good or Evil?"));
            novels.add(new NovelTemp("Sentient Super-Car", "You win a car in a sweepstakes.  It just happens to have a few extra features."));
            novels.add(new NovelTemp("Arizona Lewis and the Adventure of the Atlantean Artifact", "You choose the destiny of a world renowned adventurer in a tale of international intrigue"));

            novels.get(0).storyFields.add("fnlnpn");
            novels.get(1).storyFields.add("fnlnpn");
            novels.get(1).storyFields.add("what is your favorite color:");
            novels.get(1).storyFields.add("name a color that goes well with your favorite:");
            novels.get(2).storyFields.add("fnlnpn");
            novels.get(2).storyFields.add("what is your favorite color of car:");
            novels.get(2).storyFields.add("what is your favorite make/model of car:");

            novels.get(1).customTags.add("#color#");
            novels.get(1).customTags.add("#color2#");
            novels.get(2).customTags.add("#color#");
            novels.get(2).customTags.add("#car#");
        }

        // populate pages and choices for novels
        if(novels.get(0).getPages().isEmpty() ){
            novels.get(0).pages.add(new PageTemp(1, "Prologue", fileInput("DC-1.txt")));
            novels.get(0).pages.get(0).getChoices().add(new ChoiceTemp("Back away slowly.")); //choice(0) on page(0)
            novels.get(0).pages.get(0).getChoices().add(new ChoiceTemp("Step into the portal."));  // choice(1) on page(0)

            novels.get(0).pages.add(new PageTemp(2, novels.get(0).pages.get(0).getChoices().get(0).getChoiceDescription(), fileInput("DC-2.txt")));
            novels.get(0).pages.get(0).getChoices().get(0).setPageId(novels.get(0).pages.get(1).getId());

            novels.get(0).pages.add(new PageTemp(3, novels.get(0).pages.get(0).getChoices().get(1).getChoiceDescription(), fileInput("DC-3.txt")));
            novels.get(0).pages.get(0).getChoices().get(1).setPageId(novels.get(0).pages.get(2).getId());
            novels.get(0).pages.get(2).getChoices().add(new ChoiceTemp("Continue Reading ->"));

            novels.get(0).pages.add(new PageTemp(4, novels.get(0).pages.get(2).getChoices().get(0).getChoiceDescription(), fileInput("DC-4.txt")));
            novels.get(0).pages.get(2).getChoices().get(0).setPageId(novels.get(0).pages.get(3).getId());
        }

        List<String> recentlyUpdated = new ArrayList<>();

        for (int i = 0; i < novels.size(); i++) {
            recentlyUpdated.add(novels.get(i).getNovelTitle());
        }

        model.addAttribute("recent", recentlyUpdated);
        model.addAttribute("title", "accept your quest");

        return "novel/index";
    }

    // Request path: /novel/title-page/{novelId}
    @RequestMapping(value = "title-page/{novelId}", method = RequestMethod.GET)
    public String titlePage(Model model, @PathVariable int novelId) {

        model.addAttribute("novel", novels.get(novelId-1).getNovelTitle());
        model.addAttribute("subTitle", novels.get(novelId-1).getSubTitle());
        model.addAttribute("novelId", novelId);
        model.addAttribute("title", "accept your quest");

        return "novel/title-page";
    }

    // Request path: /novel/story-fields/{novelId}
    @RequestMapping(value = "story-fields/{novelId}", method = RequestMethod.GET)
    public String storyFields(Model model, @PathVariable int novelId) {



        model.addAttribute("novel", novels.get(novelId-1));
        model.addAttribute("novelId", novelId);
        model.addAttribute("storyFields", novels.get(novelId-1).getStoryFields());
        model.addAttribute("title", "story fields");
        model.addAttribute("pronouns", Pronouns.values());

        return "novel/story-fields";
    }

    @RequestMapping(value = "{novelId}/{pageId}", method = RequestMethod.POST)
    public String pageDisplay(Model model, @PathVariable int novelId, @PathVariable int pageId, @RequestParam String firstName,
                        @RequestParam String lastName) {

            model.addAttribute("pageTxt", novels.get(novelId-1).getPages().get(pageId-1).getPageText());
            model.addAttribute("title", novels.get(novelId-1).getNovelTitle());
            model.addAttribute("novelId", novelId);
            model.addAttribute("pageID", pageId);
            model.addAttribute("choices", novels.get(novelId-1).getPages().get(pageId-1).getChoices());
            model.addAttribute("subTitle", novels.get(novelId-1).getPages().get(pageId-1).getPageTitle());

            return "novel/page";
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

