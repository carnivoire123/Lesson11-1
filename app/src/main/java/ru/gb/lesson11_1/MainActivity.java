package ru.gb.lesson11_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity
        extends AppCompatActivity
        implements FirstFragment.FirstFragmentController, SecondFragment.SecondFragmentController {

    private FragmentManager fragmentManager;

    // является ли внешний вид активити двухсторонним
    // видно ли на экране активити второй фрагмент
    private boolean doubleSided = false;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    public static final String FIRST_TAG = "FIRST_TAG";
    public static final String SECOND_TAG = "SECOND_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        doubleSided = (findViewById(R.id.main_second_host) != null);

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.main_first_host, new FirstFragment(), FIRST_TAG)
                    //.addToBackStack(null)
                    .commit();
        }

        firstFragment = (FirstFragment) fragmentManager.findFragmentByTag(FIRST_TAG);
        secondFragment = (SecondFragment) fragmentManager.findFragmentByTag(SECOND_TAG);

        if (secondFragment != null) {
            // выбрасываем транзакцию со вторым фрагментом из бэкстэка
            fragmentManager
                    .popBackStack(SECOND_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

//            fragmentManager
//                    .beginTransaction()
//                    .remove(secondFragment)
//                    .commit()
//            ;

            fragmentManager.executePendingTransactions();



            if (doubleSided) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.main_second_host, secondFragment, SECOND_TAG)
                        .addToBackStack(SECOND_TAG)
                        .commit();
            } else {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.main_first_host, secondFragment, SECOND_TAG)
                        .addToBackStack(SECOND_TAG)
                        .commit();
            }


        }

    }

    @Override
    public void startSecond() {

        if (secondFragment == null)
            secondFragment = new SecondFragment();

        if (!secondFragment.isVisible()) {
            if (doubleSided) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.main_second_host, secondFragment, SECOND_TAG)
                        .addToBackStack(SECOND_TAG)
                        .commit();
            } else {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.main_first_host, secondFragment, SECOND_TAG)
                        .addToBackStack(SECOND_TAG)
                        .commit();
            }
        }

    }

    @Override
    public void endSecond() {
        fragmentManager
                .popBackStack(SECOND_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}