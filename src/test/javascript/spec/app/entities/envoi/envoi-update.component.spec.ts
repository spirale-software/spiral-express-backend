import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SpiralExpressTestModule } from '../../../test.module';
import { EnvoiUpdateComponent } from 'app/entities/envoi/envoi-update.component';
import { EnvoiService } from 'app/entities/envoi/envoi.service';
import { Envoi } from 'app/shared/model/envoi.model';

describe('Component Tests', () => {
  describe('Envoi Management Update Component', () => {
    let comp: EnvoiUpdateComponent;
    let fixture: ComponentFixture<EnvoiUpdateComponent>;
    let service: EnvoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [EnvoiUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EnvoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EnvoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EnvoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Envoi(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Envoi();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
