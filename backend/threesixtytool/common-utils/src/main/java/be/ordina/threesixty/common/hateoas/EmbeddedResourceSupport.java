package be.ordina.threesixty.common.hateoas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.util.Assert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Refactor into spring-hateoas
 */
public class EmbeddedResourceSupport extends ResourceSupport {

	private Map<String, Object> embedded;

	public EmbeddedResourceSupport() {
		super();
		embedded = new HashMap<String, Object>();
	}

	/**
	 * Returns all {@link ResourceSupport}s contained in this resource.
	 * 
	 * @return all {@link ResourceSupport}s contained in this resource
	 */
	@JsonInclude(Include.NON_EMPTY)
	@JsonProperty("_embedded")
	@XmlElement(name = "_embedded", namespace = Link.ATOM_NAMESPACE)
	public Map<String, Object> getEmbeddedResources() {
		return embedded;
	}

	/**
	 * Adds the given resource to the embedded map under the given relationship.
	 * 
	 * @param relationship
	 *            the relationship to link the given resource
	 * 
	 * @param resource
	 *            the resource to link by the given relationship
	 */
	public <T extends ResourceSupport> void put(final String relationship,
			final T resource) {
		Assert.notNull(relationship, "Relationship must not be null!");
		Assert.notNull(resource, "Resources must not be null!");
		this.embedded.put(relationship, resource);
	}

	/**
	 * Adds the given Set of resources to the embedded map under the given
	 * relationship.
	 * 
	 * @param relationship
	 *            the relationship to link the given resources
	 * 
	 * @param resources
	 *            the Set of resources to link by the given relationship
	 */
	public void put(final String relationship,
			final Set<? extends ResourceSupport> resources) {
		Assert.notNull(relationship, "Relationship must not be null!");
		Assert.notNull(resources, "Resources must not be null!");
		this.embedded.put(relationship, resources);
	}

	/**
	 * Returns whether the resource contains embedded {@link ResourceSupport}s
	 * at all.
	 * 
	 * @return whether the resource contains embedded {@link ResourceSupport}s
	 */
	public boolean hasEmbeddedResources() {
		return !this.embedded.isEmpty();
	}

	/**
	 * Returns whether the resource contains an embedded {@link ResourceSupport}
	 * with the given rel.
	 * 
	 * @param relationship
	 *            the relationship to check for resources
	 * 
	 * @return whether the resource contains an embedded {@link ResourceSupport}
	 *         with the given relationship
	 */
	public boolean hasEmbeddedResources(final String relationship) {
		return getEmbeddedResourcesByRel(relationship) != null;
	}

	/**
	 * Removes all embedded {@link ResourceSupport}s added to the
	 * embeddedResources so far.
	 */
	public void removeEmbeddedResources() {
		this.embedded.clear();
	}

	/**
	 * Returns the {@link ResourceSupport} linked to the given rel.
	 * 
	 * @param rel
	 *            the relationship
	 * 
	 * @return the embedded resource with the given rel or {@literal null} if
	 *         none found.
	 * 
	 * @throws NullPointerException
	 *             if the specified key is null
	 */

	public ResourceSupport getEmbeddedResourceByRel(final String rel) {
		return (ResourceSupport) embedded.get(rel);
	}

	/**
	 * Returns the Set of {@link ResourceSupport}s linked to the given rel.
	 * 
	 * @param rel
	 *            the relationship
	 * 
	 * @return the embedded Set of resources with the given rel or
	 *         {@literal null} if none found.
	 * 
	 * @throws NullPointerException
	 *             if the specified key is null
	 */
	public Set<ResourceSupport> getEmbeddedResourcesByRel(final String rel) {
		return (Set<ResourceSupport>) embedded.get(rel);
	}

	/**
	 * For Json deserialization
	 */
	private void set_embedded(final Map<String, Object> embedded) {
		this.embedded = embedded;
	}

}