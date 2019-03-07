package phong.FAA_GUI;

import java.util.Comparator;
import java.util.Map;

class MapComparator implements Comparator<Map<String, Double>>
{
    private final String key;

    public MapComparator(String key)
    {
        this.key = key;
    }

    public int compare(Map<String, Double> first,
                       Map<String, Double> second)
    {
        // TODO: Null checking, both for maps and values
        Double firstValue = first.get(key);
        Double secondValue = second.get(key);
        return firstValue.compareTo(secondValue);
    }
}