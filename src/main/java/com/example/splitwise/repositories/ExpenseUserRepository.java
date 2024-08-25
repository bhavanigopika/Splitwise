package com.example.splitwise.repositories;

import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    //Already coming here as parameter with the particular user only. For that particular user, I am finding all the expense
    List<ExpenseUser> findAllByUser(User user);
}

/*
ExpenseUser -> Expense, User, Amount,  PAID_BY / HAD_TO_PAY
 */