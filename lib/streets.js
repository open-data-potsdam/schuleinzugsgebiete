function searchByName(name) {
  return [{name: "foo", houseNumber: "1 - 47", school: "bar"},
          {name: "bar", school: "foo"}];
}

function findBySchool(schoolId) {
  return [];
}

module.exports = {
  searchByName: searchByName,
  findBySchool: findBySchool
};
