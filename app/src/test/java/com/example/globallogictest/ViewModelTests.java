package com.example.globallogictest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.globallogictest.data.Info;
import com.example.globallogictest.viewmodels.InfoViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ViewModelTests {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private InfoViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = Mockito.mock(InfoViewModel.class);
    }

    @Test
    public void testNull() {
        when(viewModel.getAllInfo()).thenReturn(null);
        assertNull(viewModel.getAllInfo());
    }

    @Test
    public void testRetrieveInfo() {
        MutableLiveData<List<Info>> someLiveData = new MutableLiveData<>();
        when(viewModel.getAllInfo()).thenReturn(someLiveData);
        assertNotNull(viewModel.getAllInfo());
    }

    @Test
    public void testInsert() {
        Info info = new Info(1,"2","3","4");
        viewModel.insert(info);
        verify(viewModel).insert(info);
    }


    @Test
    public void testDelete() {
        viewModel.deleteInfo();
        verify(viewModel).deleteInfo();
    }

    @After
    public void tearDown() throws Exception {
        viewModel = null;
    }
}
