package harkka.boardgame.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harkka.boardgame.domain.Make;
import harkka.boardgame.domain.MakeRepository;

@Controller
public class MakeController {
	
	@Autowired
	private MakeRepository mrepository;
	
	@RequestMapping(value="/makelist", method = RequestMethod.GET)
	public String makeList(Model model) {
		model.addAttribute("make", mrepository.findAll());
		return "makelist";
	}
	
    
    @RequestMapping(value = "/addmake")
    public String addMake(Model model){
    	model.addAttribute("make", new Make());
        return "addmake";
    }     
    
    @RequestMapping(value = "/savemake", method = RequestMethod.POST)
    public String save(@Valid Make make, BindingResult result, Model model){
    	 if (result.hasErrors()) {
     		model.addAttribute("maker", mrepository.findAll());
     		return "addmake";
     	}
    	mrepository.save(make);
        return "redirect:/makelist";
    }    
    
    @RequestMapping(value = "/deletemake/{id}")
    public String deleteMake(@PathVariable("id") Long id, Make make) {
    	mrepository.deleteById(id);
    	return "redirect:/makelist";

}

    @RequestMapping(value = "/editmake/{id}")
    public String editMake(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("make", mrepository.findById(id));
    	return "editmake";
    }


    @RequestMapping(value = "/updatemake/{id}", method = RequestMethod.POST)
    public String updateMake(@PathVariable("id") long makeId, Model model, Make make){
   	make.setId(makeId);
  	mrepository.save(make);
      return "redirect:/makelist";
   }    

 // RESTful service to get all makers
    @RequestMapping(value="/makes", method = RequestMethod.GET)
    public @ResponseBody List<Make> makeListRest() {	
        return (List<Make>) mrepository.findAll();
    }    

	// RESTful service to get a make by id
    @RequestMapping(value="/makes/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Make> findMakeRest(@PathVariable("id") Long makeId) {	
    	return mrepository.findById(makeId);
    }      
    
    // RESTful service to save a new make
    @RequestMapping(value="/makes", method = RequestMethod.POST)
    public @ResponseBody Make saveMakeRest(@RequestBody Make make) {	
    	return mrepository.save(make);
    }
    

}



