import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdresse } from 'app/shared/model/adresse.model';
import { getEntities as getAdresses } from 'app/entities/adresse/adresse.reducer';
import { IStatutEmploye } from 'app/shared/model/statut-employe.model';
import { getEntities as getStatutEmployes } from 'app/entities/statut-employe/statut-employe.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './employe.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmployeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmployeUpdate = (props: IEmployeUpdateProps) => {
  const [adresseId, setAdresseId] = useState('0');
  const [statutEmployeId, setStatutEmployeId] = useState('0');
  const [societeId, setSocieteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { employeEntity, adresses, statutEmployes, societes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/employe');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdresses();
    props.getStatutEmployes();
    props.getSocietes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...employeEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="emnaBackEndApp.employe.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.employe.home.createOrEditLabel">Create or edit a Employe</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : employeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="employe-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="employe-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="matriculeLabel" for="employe-matricule">
                  <Translate contentKey="emnaBackEndApp.employe.matricule">Matricule</Translate>
                </Label>
                <AvField
                  id="employe-matricule"
                  type="text"
                  name="matricule"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="civiliteLabel" for="employe-civilite">
                  <Translate contentKey="emnaBackEndApp.employe.civilite">Civilite</Translate>
                </Label>
                <AvField
                  id="employe-civilite"
                  type="text"
                  name="civilite"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nomNaissanceLabel" for="employe-nomNaissance">
                  <Translate contentKey="emnaBackEndApp.employe.nomNaissance">Nom Naissance</Translate>
                </Label>
                <AvField id="employe-nomNaissance" type="text" name="nomNaissance" />
              </AvGroup>
              <AvGroup>
                <Label id="nomUsageLabel" for="employe-nomUsage">
                  <Translate contentKey="emnaBackEndApp.employe.nomUsage">Nom Usage</Translate>
                </Label>
                <AvField
                  id="employe-nomUsage"
                  type="text"
                  name="nomUsage"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="prenomLabel" for="employe-prenom">
                  <Translate contentKey="emnaBackEndApp.employe.prenom">Prenom</Translate>
                </Label>
                <AvField
                  id="employe-prenom"
                  type="text"
                  name="prenom"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateNaissanceLabel" for="employe-dateNaissance">
                  <Translate contentKey="emnaBackEndApp.employe.dateNaissance">Date Naissance</Translate>
                </Label>
                <AvField
                  id="employe-dateNaissance"
                  type="date"
                  className="form-control"
                  name="dateNaissance"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="villeNaissanceLabel" for="employe-villeNaissance">
                  <Translate contentKey="emnaBackEndApp.employe.villeNaissance">Ville Naissance</Translate>
                </Label>
                <AvField
                  id="employe-villeNaissance"
                  type="text"
                  name="villeNaissance"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="departementNaissanceLabel" for="employe-departementNaissance">
                  <Translate contentKey="emnaBackEndApp.employe.departementNaissance">Departement Naissance</Translate>
                </Label>
                <AvField id="employe-departementNaissance" type="text" name="departementNaissance" />
              </AvGroup>
              <AvGroup>
                <Label id="paysNaisanceLabel" for="employe-paysNaisance">
                  <Translate contentKey="emnaBackEndApp.employe.paysNaisance">Pays Naisance</Translate>
                </Label>
                <AvField
                  id="employe-paysNaisance"
                  type="text"
                  name="paysNaisance"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="numeroSecuriteSocialeLabel" for="employe-numeroSecuriteSociale">
                  <Translate contentKey="emnaBackEndApp.employe.numeroSecuriteSociale">Numero Securite Sociale</Translate>
                </Label>
                <AvField
                  id="employe-numeroSecuriteSociale"
                  type="text"
                  name="numeroSecuriteSociale"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="employe-email">
                  <Translate contentKey="emnaBackEndApp.employe.email">Email</Translate>
                </Label>
                <AvField
                  id="employe-email"
                  type="text"
                  name="email"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="telephoneFixLabel" for="employe-telephoneFix">
                  <Translate contentKey="emnaBackEndApp.employe.telephoneFix">Telephone Fix</Translate>
                </Label>
                <AvField id="employe-telephoneFix" type="text" name="telephoneFix" />
              </AvGroup>
              <AvGroup>
                <Label id="telephonePortableLabel" for="employe-telephonePortable">
                  <Translate contentKey="emnaBackEndApp.employe.telephonePortable">Telephone Portable</Translate>
                </Label>
                <AvField
                  id="employe-telephonePortable"
                  type="text"
                  name="telephonePortable"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="faxLabel" for="employe-fax">
                  <Translate contentKey="emnaBackEndApp.employe.fax">Fax</Translate>
                </Label>
                <AvField
                  id="employe-fax"
                  type="text"
                  name="fax"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="salaireHoraireLabel" for="employe-salaireHoraire">
                  <Translate contentKey="emnaBackEndApp.employe.salaireHoraire">Salaire Horaire</Translate>
                </Label>
                <AvField
                  id="employe-salaireHoraire"
                  type="string"
                  className="form-control"
                  name="salaireHoraire"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="salaireBrutMensuelleLabel" for="employe-salaireBrutMensuelle">
                  <Translate contentKey="emnaBackEndApp.employe.salaireBrutMensuelle">Salaire Brut Mensuelle</Translate>
                </Label>
                <AvField
                  id="employe-salaireBrutMensuelle"
                  type="string"
                  className="form-control"
                  name="salaireBrutMensuelle"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="heuresMensuelleLabel" for="employe-heuresMensuelle">
                  <Translate contentKey="emnaBackEndApp.employe.heuresMensuelle">Heures Mensuelle</Translate>
                </Label>
                <AvField
                  id="employe-heuresMensuelle"
                  type="string"
                  className="form-control"
                  name="heuresMensuelle"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="categorieLabel" for="employe-categorie">
                  <Translate contentKey="emnaBackEndApp.employe.categorie">Categorie</Translate>
                </Label>
                <AvField
                  id="employe-categorie"
                  type="text"
                  name="categorie"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="posteLabel" for="employe-poste">
                  <Translate contentKey="emnaBackEndApp.employe.poste">Poste</Translate>
                </Label>
                <AvField
                  id="employe-poste"
                  type="text"
                  name="poste"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateEmbaucheLabel" for="employe-dateEmbauche">
                  <Translate contentKey="emnaBackEndApp.employe.dateEmbauche">Date Embauche</Translate>
                </Label>
                <AvField
                  id="employe-dateEmbauche"
                  type="date"
                  className="form-control"
                  name="dateEmbauche"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateSortieLabel" for="employe-dateSortie">
                  <Translate contentKey="emnaBackEndApp.employe.dateSortie">Date Sortie</Translate>
                </Label>
                <AvField
                  id="employe-dateSortie"
                  type="date"
                  className="form-control"
                  name="dateSortie"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="typeContratLabel" for="employe-typeContrat">
                  <Translate contentKey="emnaBackEndApp.employe.typeContrat">Type Contrat</Translate>
                </Label>
                <AvField
                  id="employe-typeContrat"
                  type="text"
                  name="typeContrat"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="situationFamilialeLabel" for="employe-situationFamiliale">
                  <Translate contentKey="emnaBackEndApp.employe.situationFamiliale">Situation Familiale</Translate>
                </Label>
                <AvField
                  id="employe-situationFamiliale"
                  type="text"
                  name="situationFamiliale"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="enfantsAChargeLabel" for="employe-enfantsACharge">
                  <Translate contentKey="emnaBackEndApp.employe.enfantsACharge">Enfants A Charge</Translate>
                </Label>
                <AvField
                  id="employe-enfantsACharge"
                  type="string"
                  className="form-control"
                  name="enfantsACharge"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="employe-adresse">
                  <Translate contentKey="emnaBackEndApp.employe.adresse">Adresse</Translate>
                </Label>
                <AvInput id="employe-adresse" type="select" className="form-control" name="adresseId">
                  <option value="" key="0" />
                  {adresses
                    ? adresses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="employe-statutEmploye">
                  <Translate contentKey="emnaBackEndApp.employe.statutEmploye">Statut Employe</Translate>
                </Label>
                <AvInput id="employe-statutEmploye" type="select" className="form-control" name="statutEmployeId" required>
                  {statutEmployes
                    ? statutEmployes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>
                  <Translate contentKey="entity.validation.required">This field is required.</Translate>
                </AvFeedback>
              </AvGroup>
              <AvGroup>
                <Label for="employe-societe">
                  <Translate contentKey="emnaBackEndApp.employe.societe">Societe</Translate>
                </Label>
                <AvInput id="employe-societe" type="select" className="form-control" name="societeId">
                  <option value="" key="0" />
                  {societes
                    ? societes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/employe" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  adresses: storeState.adresse.entities,
  statutEmployes: storeState.statutEmploye.entities,
  societes: storeState.societe.entities,
  employeEntity: storeState.employe.entity,
  loading: storeState.employe.loading,
  updating: storeState.employe.updating,
  updateSuccess: storeState.employe.updateSuccess
});

const mapDispatchToProps = {
  getAdresses,
  getStatutEmployes,
  getSocietes,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmployeUpdate);
