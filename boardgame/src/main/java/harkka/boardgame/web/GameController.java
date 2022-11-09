package harkka.boardgame.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harkka.boardgame.domain.Category;
import harkka.boardgame.domain.CategoryRepository;
import harkka.boardgame.domain.Game;
import harkka.boardgame.domain.GameRepository;
import harkka.boardgame.domain.Make;
import harkka.boardgame.domain.MakeRepository;


@Controller
public class GameController {
	
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private MakeRepository mrepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/gamelist", method = RequestMethod.GET)
	public String gameList(Model model) {
		model.addAttribute("games", repository.findAll());
		return "gamelist";
	}
	
    
    @RequestMapping(value = "/add")
    public String addGame(Model model){
    	model.addAttribute("game", new Game());
    	model.addAttribute("make", mrepository.findAll());
    	model.addAttribute("category", crepository.findAll());
        return "addgame";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Game game, BindingResult result, Model model){
    	if (result.hasErrors()) {
    		model.addAttribute("make", mrepository.findAll());
    		model.addAttribute("category", crepository.findAll());
    		return "addgame";
    		}
        repository.save(game);
        return "redirect:/gamelist";
    }   
    
    @GetMapping(value="/gamelistbymaker/{id}")
    public String gameMaker(@PathVariable("id") Long id, Model model) {	
    	model.addAttribute("make", mrepository.findById(id));
       	model.addAttribute("game", repository.findAll());

        return "gamelistbymaker";
    }
    
    
    @RequestMapping(value = "/edit/{id}")
	public String editGame(@PathVariable("id") Long gameId, Model model) {
    	Game game = repository.findById(gameId)
    	.orElseThrow(() -> new IllegalArgumentException("Invalid id" + gameId));
    	
		model.addAttribute("game", game);
		model.addAttribute("make", mrepository.findAll());
		model.addAttribute("category", crepository.findAll());
		return "editgame";   
    
} 
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateGame(@PathVariable("id") long gameId, Model model,Game game, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("make", mrepository.findAll());
            model.addAttribute("category", crepository.findAll());
            return "editgame";
        }
        game.setId(gameId);
        repository.save(game);
        return "redirect:/gamelist";
    }
    
   // @RequestMapping(value = "/editlik/{id}")
	//public String editLik(@PathVariable("id") Long gameId, Model model) {
	//	model.addAttribute("game", repository.findById(gameId));
	//	model.addAttribute("make", mrepository.findAll());
	//	model.addAttribute("category", crepository.findAll());
	//	return "editgame";   
    
//} 
    @RequestMapping(value = "/editgamelik/{id}")
   	public String editlikGame(@PathVariable("id") Long gameId, Model model) {
    		Game game = repository.findById(gameId)
    		.orElseThrow(() -> new IllegalArgumentException("Invalid id" + gameId));
    		model.addAttribute("game", game);
    		model.addAttribute("category", crepository.findAll());
    		model.addAttribute("make", mrepository.findAll());
   		return "editgamelik";   
       
   }
    

    
    @RequestMapping(value = "/updatelik/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long gameId, Model model, Game game, BindingResult result){
    	if (result.hasErrors()) {
            model.addAttribute("make", mrepository.findAll());
            model.addAttribute("category", crepository.findAll());
            return "editgamelik";
        }
        game.setId(gameId);
    	repository.save(game);
        return "redirect:/gamelist";
   }    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteGame(@PathVariable("id") Long gameId, Model model) {
    	repository.deleteById(gameId);
        return "redirect:/gamelist";
    }
    
    // RESTful service to get all games
    @RequestMapping(value="/games", method = RequestMethod.GET)
    public @ResponseBody List<Game> gameListRest() {	
        return (List<Game>) repository.findAll();
    }    

	// RESTful service to get a game by id
    @RequestMapping(value="/games/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") Long gameId) {	
    	return repository.findById(gameId);
    }      
    
    // RESTful service to save a new game
    @RequestMapping(value="/games", method = RequestMethod.POST)
    public @ResponseBody Game saveGameRest(@RequestBody Game game) {	
    	return repository.save(game);
    }
    

}
