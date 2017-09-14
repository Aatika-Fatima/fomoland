package com.fomo.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.dto.OptionCount;
import com.fomo.dto.VoteResult;
import com.fomo.persistence.dao.VoteRepository;
import com.fomo.persistence.model.Vote;

import io.swagger.annotations.ApiOperation;

@RestController
public class ComputeResultController {
	@Autowired
	private VoteRepository voteRepository;

	@RequestMapping(value = "/polls/computeresult", method = RequestMethod.GET)
	@ApiOperation(value = "total votes",
			notes = "Computes the result for a given poll id")
	public ResponseEntity<?> computeResult(@RequestParam("pollId") Long pollId) {
		VoteResult voteResult = new VoteResult();
		Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
		int totalVotes = 0;
		Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
		for (Vote v : allVotes) {
			totalVotes++;
			// Get the OptionCount corresponding to this Option
			OptionCount optionCount = tempMap.get(v.getOption().getId());
			if (optionCount == null) {
				optionCount = new OptionCount();
				optionCount.setOptionId(v.getOption().getId());
				tempMap.put(v.getOption().getId(), optionCount);
			}
			optionCount.setCounts(optionCount.getCounts() + 1);
		}
		voteResult.setTotalVotes(totalVotes);
		voteResult.setResults(tempMap.values());
		return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
	}
}
