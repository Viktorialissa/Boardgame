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

import harkka.boardgame.domain.Category;
import harkka.boardgame.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@RequestMapping(value="/categorylist", method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("category", crepository.findAll());
		return "categorylist";
	}
	
    
    @RequestMapping(value = "/addcategory")
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    }     
    
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(@Valid Category category, BindingResult result, Model model){
    	if (result.hasErrors()) {
    		model.addAttribute("category", crepository.findAll());
    		return "addcategory";
    	}
    	crepository.save(category);
        return "redirect:categorylist";
    }    
    
    @RequestMapping(value = "/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Category category) {
    	crepository.deleteById(id);
    	return "redirect:/categorylist";

}

    @RequestMapping(value = "/editcategory/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("category", crepository.findById(id));
    	return "editcategory";
    }


  //  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  //  public String update(@PathVariable("id") long makeId, Model model, Game game, Make make){
 //   	make.setId(makeId);
 //   	mrepository.save(make);
 //       return "redirect:/makelist";
  //  }    

    
    
    
 // RESTful service to get all categories
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> categoryListRest() {	
        return (List<Category>) crepository.findAll();
    }    

	// RESTful service to get a category by id
    @RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long categoryId) {	
    	return crepository.findById(categoryId);
    }      
    
    // RESTful service to save a new category
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {	
    	return crepository.save(category);
    }
    

}
