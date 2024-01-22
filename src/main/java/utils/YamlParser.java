package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class YamlParser {

    private static InputStream streamYaml(final String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the list of LinkedHashMaps representing the yaml list contents
     * @param yamlPath the path to the yaml input file
     * @return the List of LinkedHashMaps representing the yaml list contents. Note: the List type is a raw type due
     *         to how the yaml file is read and parsed
     */
    public static List getYamlContents(final String yamlPath) {

        Yaml yaml = new Yaml();

        InputStream inputStream = streamYaml(yamlPath);
        Iterable<Object> dictionary = yaml.loadAll(inputStream);
        Iterator<Object> iterator = dictionary.iterator();
        Object next = iterator.next();

        return (List) next;
    }
}
