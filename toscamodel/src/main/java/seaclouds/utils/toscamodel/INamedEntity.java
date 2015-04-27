package seaclouds.utils.toscamodel;

/**
 * Created by pq on 16/04/2015.
 * All entities in tosca which have an associated, and unique, name.
 * Includes datatypes, relationships, node types and node templates
 */
public interface INamedEntity {
    String name();
}
