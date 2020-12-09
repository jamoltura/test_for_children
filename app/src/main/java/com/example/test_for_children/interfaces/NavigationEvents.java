package com.example.test_for_children.interfaces;

import android.os.Bundle;

public interface NavigationEvents {
    void home_to_work();
    void work_to_home();
    void work_to_finish();
    void finish_to_home();
    void home_to_elements();
    void element_to_home();
    void element_to_onlyelement();
    void element_to_onlyelement(Bundle bundle);
}
