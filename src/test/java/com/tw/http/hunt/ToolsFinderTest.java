package com.tw.http.hunt;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolsFinderTest extends TestCase {

    public void testFind1() {
        String treasure = "opekandifehgujnsr";
        List<String> toolsInput = Arrays.asList("knife", "guns", "rope");
        List<String> expected = Arrays.asList("knife", "guns");

        ToolsFinder finder = new ToolsFinder(treasure, toolsInput);
        ArrayList<String> actual = finder.find();
        Assert.assertEquals(expected, actual);
    }
    public void testFind2() {
        String treasure = "fasfaladsfadsaafdsrfadsfesferwiterwrgstfadsgifghdugurynrgrdfovdfpgsge";
        List<String> toolsInput = Arrays.asList("flare", "firstaid", "crowbar", "gun", "rope");
        List<String> expected = Arrays.asList("flare", "firstaid", "gun", "rope");

        ToolsFinder finder = new ToolsFinder(treasure, toolsInput);
        ArrayList<String> actual = finder.find();
        Assert.assertEquals(expected, actual);
    }
}