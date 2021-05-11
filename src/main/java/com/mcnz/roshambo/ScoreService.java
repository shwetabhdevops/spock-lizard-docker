package com.mcnz.roshambo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//CrossOrigin
@RestController
public class ScoreService {
	
	Score score = new Score();
	
	@GetMapping("/increasewins")
	public Score increaseWins() {
		score.wins++;
		return score;
	}
	@GetMapping("/increaselosses")
	public Score increaseLosses() {
		score.losses++;
		return score;
	}
	@GetMapping("/increaseties")
	public Score increaseTies() {
		score.ties++;
		return score;
	}

}
