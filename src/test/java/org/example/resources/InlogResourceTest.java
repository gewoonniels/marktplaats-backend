package org.example.resources;

import org.example.domain.Gebruiker;
import org.example.domain.dao.GebruikerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InlogResourceTest {

    @Mock
    private GebruikerDao gebruikerDao;

    @InjectMocks
    private InlogResource InlogResource;

    @Test
    void loginWithCorrectInformationShouldGiveUser() {
        //given
        when(gebruikerDao.login("niels", "wachtwoord")).thenReturn(new Gebruiker("niels", "wachtwoord", "test"));
        // when
        Gebruiker result = InlogResource.login(new Gebruiker("niels", "wachtwoord", "test"));
        //then
        assertThat(result.getNaam(), is("test"));
    }

    @Test
    void loginWithIncorrectPasswordThrowsError() {
        //given
        when(gebruikerDao.login("niels", "nietwachtwoord")).thenThrow(SecurityException.class);;
        //then
        Assertions.assertThrows(RuntimeException.class, () -> {
            //when
            Gebruiker result = InlogResource.login(new Gebruiker("niels", "nietwachtwoord", "test"));
        });
    }

    @Test
    void loginWithIncorrectEmailThrowsError() {
        //given
        when(gebruikerDao.login("nietniels", "wachtwoord")).thenThrow(SecurityException.class);
        //then
        Assertions.assertThrows(RuntimeException.class, () -> {
            //when
            Gebruiker result = InlogResource.login(new Gebruiker("nietniels", "wachtwoord", "test"));
        });
    }


}