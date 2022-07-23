package de.tum.in.ase.eist;

import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {

    // TODO implement the tests

    private static final int YEAR = 2000;
    private static final int MONTH = 9;
    private static final int DAY = 23;
    @TestSubject
    private Discussion discussion = new Discussion();

    @Mock
    private Course courseMock;
    @Mock
    private Comment commentMock;

    @Test
    void testComment() {
        int expectedSize = discussion.getNumberOfComments() + 1;

        expect(commentMock.save()).andReturn(true);
        replay(commentMock);
        discussion.addComment(commentMock);
        verify(commentMock);

        assertEquals(expectedSize, discussion.getNumberOfComments());
    }

    @Test
    void testCommentIfSavingFails() {

        int expectedSize = discussion.getNumberOfComments();

        expect(commentMock.save()).andReturn(false);
        replay(commentMock);
        discussion.addComment(commentMock);
        verify(commentMock);

        assertEquals(expectedSize, discussion.getNumberOfComments());
    }

    @Test
    void testStartCourseDiscussion() {

        Student student = new Student("Luffy", "Monkey.D", LocalDate.of(YEAR, MONTH, DAY), "java advanced", "C++ advanced");
        expect(courseMock.isDiscussionAllowed(student)).andReturn(true);
        //expect(discussion.startCourseDiscussion(courseMock, student, "How to become Pirate King?"));
        replay(courseMock);

        //discussion.startCourseDiscussion(courseMock, student, "How to become Pirate King?");
        assertTrue(discussion.startCourseDiscussion(courseMock, student, "How to become Pirate King?"));
        assertEquals(courseMock, discussion.getCourse());
        assertEquals("How to become Pirate King?", discussion.getTopic());

        //(discussion.startCourseDiscussion(courseMock, student, "How to become Pirate King?"));
        verify(courseMock);
        //verify(courseMock.isDiscussionAllowed(student));

    }


}
