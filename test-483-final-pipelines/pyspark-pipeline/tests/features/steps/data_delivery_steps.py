"""
Implementation steps for data_delivery.feature.

GENERATED STUB CODE - PLEASE ***DO*** MODIFY

Originally generated from: templates/behave.steps.py.vm
"""

from behave import given, when, then  # pylint: disable=no-name-in-module
from os import path, walk
import nose.tools as nt
import ast
import sys
from pathlib import Path


@given("python files are generated")
def step_impl(context):
    return


@then("the generated files are syntactically correct")
def step_impl(context):
    test_staging_directory = Path(__file__).resolve().parents[2]
    for root, dirs, files in walk(test_staging_directory):
        for file in files:
            if file.endswith(".py"):
                nt.ok_(
                    is_valid_python(path.join(root, file)),
                    "File {0} does not have valid python syntax".format(file),
                )


def is_valid_python(fname):
    with open(fname) as f:
        contents = f.read()
    try:
        ast.parse(contents)
        return True
    except SyntaxError:
        return False
