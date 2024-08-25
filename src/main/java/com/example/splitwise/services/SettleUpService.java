package com.example.splitwise.services;

import com.example.splitwise.Strategy.SettleUpStrategy;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.ExpenseRepository;
import com.example.splitwise.repositories.ExpenseUserRepository;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

    public SettleUpService(UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository,
                           SettleUpStrategy settleUpStrategy, GroupRepository groupRepository,
                           ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }
    public List<Expense> settleUpUser(Long userId){

        /*
        1. Get the user object from the user table => So, go with UserRepository
        2. Get all the expenses(paid_by, had_to_pay) this user was a part of (i.e) say, krish is a user, and
           he is a part of multiple expenses (Coffee, dinner, car booking...) I want to know all the expenses of krish
           -> so I can go with ExpenseUser object(ExpenseUser.java) => So, go with ExpenseUserRepository
        3. Iterate through all expenses and find out total extra amount or total lesser amount paid by every user involved - Refer HeapSettleUpStrategy.java
        4. Then, apply the max or min heap algorithm to settle up everyone
         */
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid userId" + userId);
        }
        //if optionalUser is not empty
        User user = optionalUser.get();

        /********************************************************************************/

        //I want to know all the expense(paid_by or had_to_pay) of individual user, here I am passing the parameter of particular user
        List<ExpenseUser> expenseUserList = expenseUserRepository.findAllByUser(user);

        /*
        * Here we get list of expenses both the ways that is he paid (or) he had to pay
        * List<Expense> expenseList = new ArrayList<>();
        * But, we need unique expense details, so I used Set -> HashSet
        * */
        Set<Expense> expenseSet = new HashSet<>();
        //Get all the expense list of individual user, so that we know paid_by and had_to_pay of individual user
        for(ExpenseUser expenseUser : expenseUserList){
            //add the complete expense object only(not other user, amount, expenseUserType) from ExpenseUser in HashSet,
            //so that we can get expense of individual user
            expenseSet.add(expenseUser.getExpense());//get expense object which is represented by expense_id
        }

        /********************************************************************************/

        //SettleUp the expenses

        /*I am passing the expenseSet not expenseList here as parameter and return the list of transaction
        * And we don't have any transaction class, So, I return it as in list<Expense> and name it as TransactionList or TransactionToBeDone
        * Here, TransactionToBeDone in List<Expense> are dummy expense
        * Here, expenseSet is Set not List. So, I convert it into list by expenseSet.stream().toList() or List<Expense> expenseSet
        * */
        //List<Expense>  transactionList = settleUpStrategy.settleUp((List<Expense>) expenseSet);
        //or
        List<Expense> transactionToBeDone = settleUpStrategy.settleUp(expenseSet.stream().toList());

        /*Here,you are passing the set of expense and then return all the transactions to be done to settle up
        * And this transaction algorithm(TransactionToBeDone) will try to settle up all the users and settle up all the expense.
        * But we don't want to settle up all the users here. We want that, when user click on settle up and he is interested
        * to settle up to what he is involved in, then filter out those expenses (belong to that user is part of).
        * */
        List<Expense> expensesToReturn = new ArrayList<>();

        for(Expense expense : transactionToBeDone){
            //for every expense we have expenseUser
            for(ExpenseUser expenseUser : expense.getExpenseUserList()){
                //equate with the current user
                if(expenseUser.getUser().equals(user)){
                    expensesToReturn.add(expenseUser.getExpense());
                }
            }
        }

        return expensesToReturn;
    }

    public List<Expense> settleUpGroup(Long groupId){

        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if(optionalGroup.isEmpty()){
            throw new RuntimeException("Invalid groupId" + groupId);
        }

        Group group = optionalGroup.get();

        /*
        Once get the group,
        In the given group, we have list of expense List<Expense> -> but we used mapped by to avoid duplicate one
        So, use in expense,  we have a group(group_id)...From expenseRepository, get all the expenses using group(group_id)
        */
        List<Expense> expenses = expenseRepository.findAllByGroup(group);

        //Now, I want to settle up all the groups...no problem like we want individual group...group that is common, so directly return
        return settleUpStrategy.settleUp(expenses);
    }
}
