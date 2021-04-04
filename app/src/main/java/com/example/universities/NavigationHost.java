package com.example.universities;

import androidx.fragment.app.Fragment;

public interface NavigationHost {
    public void navigateTo(Fragment fragment, boolean addToBackStack);
}
