package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Firestation;

import java.util.List;

public interface IFirestationsProxy {
    public List<Firestation> readFirestations();
   
}
