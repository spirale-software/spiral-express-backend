package io.spiral.express.jhipster;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("io.spiral.express.jhipster");

        noClasses()
            .that()
                .resideInAnyPackage("io.spiral.express.jhipster.service..")
            .or()
                .resideInAnyPackage("io.spiral.express.jhipster.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..io.spiral.express.jhipster.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
