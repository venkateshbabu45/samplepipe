package com.bank.app.controller;

import com.bank.app.domain.Bank;
import com.bank.app.domain.Branch;
import com.bank.app.serviceImpl.BranchServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/branch")
@JsonIgnoreProperties
public class BranchController {
/*
	@Autowired
	Producer producer;

	@Autowired
	Consumer consumer;*/

    Branch branch = new Branch();

    /*@Autowired
    BranchServiceProxy branchServiceProxy;*/

    @Autowired
    private BranchServiceImpl branchServiceImpl;

    @RequestMapping(value = "/getBranchById/{branchCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getBranchById(@PathVariable Integer branchCode) throws JSONException {
        //Collection<Bank> list = branchServiceProxy.findAll();
        String branch = branchServiceImpl.getBranchById(branchCode);
        return branch;
    }

    JSONObject jsonObj;

    @RequestMapping(value = "/getBranchInfo/{branchCode}", method = RequestMethod.GET)
    @ResponseBody
    public Branch getBranchInfo(@PathVariable Integer branchCode) {
        Branch branch = branchServiceImpl.getBranchInfo(branchCode);
        return branch;
    }

    @RequestMapping(value = "/getAllBranchs", method = RequestMethod.GET)
    public Collection<Branch> getAllBranchs() {
        System.out.println("branch--->" + branch.getBranchType());
        return branchServiceImpl.getAllBranches();
    }

    @RequestMapping(value = "/insertBranch", method = RequestMethod.POST)
    public HttpStatus insertBranch(@RequestBody Branch branch) {
        boolean hasBranchInfo = branchServiceImpl.saveBranch(branch);

        // if(hasBranchInfo) { producer.sendBranch(branch); }

        System.out.println(branch);
        return hasBranchInfo ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/updateBranch", method = RequestMethod.PUT)
    public HttpStatus updateBranch(@RequestBody Branch branch) {
        return branchServiceImpl.updateBranch(branch) ? HttpStatus.LOCKED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/deleteBranchById/{branchCode}", method = RequestMethod.DELETE)
    public HttpStatus deleteBranch(@PathVariable Integer branchCode) throws InterruptedException {
        // System.out.println(branch.getAddress());
        branchServiceImpl.deleteBranch(branchCode);
        return HttpStatus.NO_CONTENT;
    }

}
